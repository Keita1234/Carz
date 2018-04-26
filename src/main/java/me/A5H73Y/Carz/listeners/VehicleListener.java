package me.A5H73Y.Carz.listeners;

import me.A5H73Y.Carz.Carz;
import me.A5H73Y.Carz.other.Utils;
import me.A5H73Y.Carz.other.Validation;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleUpdateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class VehicleListener implements Listener {

    private final Carz carz;

    public VehicleListener(Carz carz) {
        this.carz = carz;
    }

    @EventHandler
    public void onVehicleUpdate(VehicleUpdateEvent event) {
        if (!(event.getVehicle().getPassenger() instanceof Player))
            return;

        if (!Validation.isACarzVehicle(event.getVehicle()))
            return;

        Player player = (Player) event.getVehicle().getPassenger();
        Integer carId = event.getVehicle().getEntityId();

        if (!carz.getCarController().isDriving(player.getName()))
            return;

        if (carz.getFuelController().isFuelConsumed(carId)) {
            player.sendMessage(Utils.getTranslation("FuelEmpty"));
            carz.getCarController().removeDriver(player.getName());
            return;
        }

        Vector playerVelocity = event.getVehicle().getVelocity();
        double carSpeed = carz.getCarController().getUpgradeController().getCarSpeed(carId);

        playerVelocity.setX((player.getEyeLocation().getDirection().getX() / 140.0D) * carSpeed);
        playerVelocity.setZ((player.getEyeLocation().getDirection().getZ() / 140.0D) * carSpeed);

        if (event.getVehicle().getLocation().getBlock().isLiquid() &&
                carz.getSettings().isDestroyInLiquid()) {
            carz.getCarController().destroyCar(event.getVehicle());
            player.playEffect(player.getLocation(), Effect.EXTINGUISH, null);
            player.sendMessage(Utils.getTranslation("LiquidDamage"));
            return;
        }

        Minecart minecart = (Minecart) event.getVehicle();
        Material materialBelow = minecart.getLocation().subtract(0,1,0).getBlock().getType();

        if (materialBelow == Material.STEP || materialBelow == Material.DOUBLE_STEP)
            playerVelocity.setY(0.1D);

        event.getVehicle().setVelocity(playerVelocity);

        //TODO if smoke particle
        Location loc = event.getVehicle().getLocation().add(0, -0.6, 0);
        event.getVehicle().getWorld().spawnParticle(Particle.REDSTONE, loc, 1);

        carz.getFuelController().decreaseFuel(carId);
    }

    @EventHandler
    public void onVehicleEnter(VehicleEnterEvent event) {
        if (!(event.getEntered() instanceof Player))
            return;

        if (!Validation.isACarzVehicle(event.getVehicle()))
            return;

        if (carz.getConfig().getBoolean("UsePermissions") && !event.getEntered().hasPermission("Carz.Start"))
            return;

        Player player = (Player) event.getEntered();
        Integer carID = event.getVehicle().getEntityId();

        if (carz.getCarController().isCarOwned(carID)) {
            if (!carz.getCarController().isCarOwnedByPlayer(carID, player.getName())) {
                player.sendMessage(Utils.getTranslation("Error.Owned"));
                event.setCancelled(true);
                return;
            } else {
                player.sendMessage(Utils.getTranslation("CarUnlocked"));
            }
        }

        if (carz.getFuelController().getFuelLevel(carID) != null) {
            carz.getFuelController().displayFuelLevel(player);
        }

        if (carz.getConfig().getBoolean("Key.GiveOnCarEnter") &&
                !player.getInventory().contains(Material.STICK)) {
            player.sendMessage(Utils.getTranslation("KeyReceived"));
            player.getInventory().addItem(new ItemStack(Material.STICK));
        }
    }

    @EventHandler
    public void onEngineStart(PlayerInteractEvent event) {
        if (!(event.getAction().equals(Action.RIGHT_CLICK_AIR)))
            return;

        if (!event.getPlayer().isInsideVehicle())
            return;

        if (!Validation.isACarzVehicle((Vehicle) event.getPlayer().getVehicle()))
            return;

        if (carz.getConfig().getBoolean("UsePermission") && !event.getPlayer().hasPermission("Carz.Start"))
            return;

        if (event.getPlayer().getInventory().getItemInMainHand().getType() != Material.STICK) //TODO config
            return;

        Player player = event.getPlayer();
        Minecart minecart = (Minecart) event.getPlayer().getVehicle();

        if (carz.getCarController().isDriving(player.getName())) {
            carz.getCarController().removeDriver(player.getName());
            minecart.setMaxSpeed(0D);
            player.sendMessage(Utils.getTranslation("EngineStop"));

        } else {
            carz.getCarController().addDriver(player.getName(), player.getVehicle().getEntityId());
            minecart.setMaxSpeed(1000D);
            player.sendMessage(Utils.getTranslation("EngineStart"));
        }
    }

    @EventHandler
    public void onCarDeath(EntityDeathEvent event) {
        if (!(event.getEntity() instanceof Minecart))
            return;

        if (!Validation.isACarzVehicle((Vehicle) event.getEntity()))
            return;

        carz.getCarController().destroyCar((Vehicle) event.getEntity());
    }

    @EventHandler
    public void onVehicleExit(VehicleExitEvent event) {
        if (!(event.getExited() instanceof Player)) {
            return;
        }

        if (!Validation.isACarzVehicle(event.getVehicle()))
            return;

        Player player = (Player) event.getExited();

        if (carz.getCarController().isDriving(player.getName())) {
            carz.getCarController().removeDriver(player.getName());
            player.sendMessage(Utils.getTranslation("EngineStop"));

            if (carz.getCarController().isCarOwnedByPlayer(event.getVehicle().getEntityId(), player.getName())) {
                player.sendMessage(Utils.getTranslation("CarLocked"));
            }
        }
    }
}
