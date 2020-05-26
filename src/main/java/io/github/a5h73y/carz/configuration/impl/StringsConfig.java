package io.github.a5h73y.carz.configuration.impl;

import java.io.IOException;

import io.github.a5h73y.carz.configuration.CarzConfiguration;

public class StringsConfig extends CarzConfiguration {

	@Override
	protected String getFileName() {
		return "strings.yml";
	}

	/**
	 * Initialise the strings.yml on startup.
	 * Values will be defaulted if not set.
	 */
	@Override
	protected void initializeConfig() throws IOException {
		this.addDefault("Carz.Prefix", "&0[&bCarz&0]&7 ");
		this.addDefault("Carz.SignHeader", "&0[&bCarz&0]");
		this.addDefault("Carz.Commands", "To display all commands enter &f/carz cmds");
		this.addDefault("Carz.ConsoleCommands", "To display all commands enter &f/carzc cmds");
		this.addDefault("Carz.ConfigReloaded", "The config has been reloaded.");
		this.addDefault("Carz.SignRemoved", "Carz sign removed!");
		this.addDefault("Carz.CarsDestroyed", "All cars destroyed!");
		this.addDefault("Carz.SignCreated", "%TYPE% sign created.");
		this.addDefault("Carz.Heading", "-- &9&l%TEXT% &r--");

		this.addDefault("Car.Spawned", "Car Spawned.");
		this.addDefault("Car.EngineStart", "You switch the engine on.");
		this.addDefault("Car.EngineStop", "You switch the engine off.");
		this.addDefault("Car.CarLocked", "You lock the car.");
		this.addDefault("Car.CarUnlocked", "You unlock the car.");
		this.addDefault("Car.PlayerCar", "&b%PLAYER%&f's car");
		this.addDefault("Car.FuelEmpty", "Your car has run out of fuel!");
		this.addDefault("Car.LiquidDamage", "Your car has been destroyed by liquid!");
		this.addDefault("Car.UpgradeSpeed", "New top speed: &b%SPEED%");
		this.addDefault("Car.Key.Display", "&b%PLAYER%&f's key");
		this.addDefault("Car.Key.Received", "You receive a key.");
		this.addDefault("Car.Claimed", "You are now the owner of this car.");

		this.addDefault("Purchase.Confirm.Purchase", "&7Enter &a/carz confirm &7to confirm, or &c/carz cancel &7to cancel the purchase.");
		this.addDefault("Purchase.Confirm.Car", "You are about to purchase a &b%TYPE% &7car, costing &b%COST%%CURRENCY%&7.");
		this.addDefault("Purchase.Confirm.Upgrade", "You are about to upgrade your car from &b%FROM% &7to &b%TO%&7, costing &b%COST%%CURRENCY%&7.");
		this.addDefault("Purchase.Confirm.Refuel", "You are about to refuel &b%PERCENT% &7of your car's fuel, costing &b%COST%%CURRENCY%&7.");
		this.addDefault("Purchase.Success.Car", "&f%TYPE% &7car Purchased!");
		this.addDefault("Purchase.Success.Upgrade", "Car Upgraded!");
		this.addDefault("Purchase.Success.Refuel", "Car Refuelled!");
		this.addDefault("Purchase.Cancelled", "Purchase cancelled.");

		this.addDefault("CarType.Create.Name", "&d What would you like this car to be called?");
		this.addDefault("CarType.Create.StartMaxSpeed", "&d What should the Car's Start Speed be?\n&a (default = 60.0)");
		this.addDefault("CarType.Create.MaxUpgradeSpeed", "&d What should the Car's Max Upgrade Speed be?\n&a (default = 120.0)");
		this.addDefault("CarType.Create.Acceleration", "&d What should the Car's Acceleration be?\n&a (default = 1.0)");
		this.addDefault("CarType.Create.FuelUsage", "&d What should the Fuel Usage be?\n&a (default = 1.0)");
		this.addDefault("CarType.Create.FillMaterial", "&d What should the Fill Material be?\n&a (default = AIR)");
		this.addDefault("CarType.Create.Cost", "&d How much should the car cost?\n&a (default = 10.0)");
		this.addDefault("CarType.Create.Success", "&d All done, &a%VALUE% &dcreated.");
		this.addDefault("CarType.Error.InvalidName", "Invalid Car Type name.");
		this.addDefault("CarType.Error.InvalidValue", "Invalid Value.");
		this.addDefault("CarType.Error.AlreadyExists", "This Car Type already exists.");

		this.addDefault("BlockTypes.Added.List", "&b%MATERIAL% &7added to &b%TYPE% &7blocks.");
		this.addDefault("BlockTypes.Added.Amount", "&b%MATERIAL% &7added to &b%TYPE% &7blocks, with an amount of &b%AMOUNT%&7.");
		this.addDefault("BlockTypes.Removed", "&b%MATERIAL% &7removed from &b%TYPE% &7blocks.");

		this.addDefault("Error.NoPermission", "You do not have permission: &b%PERMISSION%");
		this.addDefault("Error.SignProtected", "This sign is protected.");
		this.addDefault("Error.UnknownCommand", "Unknown Command.");
		this.addDefault("Error.UnknownSignCommand", "Unknown Sign Command.");
		this.addDefault("Error.CommandDisabled", "This command has been disabled.");
		this.addDefault("Error.InCar", "You are already in a car.");
		this.addDefault("Error.NotInCar", "You are not in a car.");
		this.addDefault("Error.HaveCar", "You already have a car.");
		this.addDefault("Error.FuelDisabled", "Fuel is disabled.");
		this.addDefault("Error.PurchaseFailed", "Purchase failed. Cost: %COST%");
		this.addDefault("Error.FullyUpgraded", "Your car is already fully upgraded.");
		this.addDefault("Error.Owned", "This car is owned by %PLAYER%!");
		this.addDefault("Error.UnknownCarType", "Unknown car type.");
		this.addDefault("Error.UnknownPlayer", "Unknown player.");
		this.addDefault("Error.UnknownMaterial", "Unknown Material: ");
		this.addDefault("Error.SpecifyPlayer", "Please specify a player.");
		this.addDefault("Error.PurchaseOutstanding", "You have an outstanding purchase.");
		this.addDefault("Error.NoPurchaseOutstanding", "You don't have an outstanding purchase.");
		this.addDefault("Error.CarNotDriven", "This can hasn't been driven yet.");
		this.addDefault("Error.TooManyArgs", "Too many arguments.");
		this.addDefault("Error.NotEnoughArgs", "Not enough arguments.");
		this.addDefault("Error.OwnedCarsLimit", "You have reached the amount of Owned cars you can place.");
		this.addDefault("Error.InvalidPlaceableMaterial", "You are unable to place a Car here.");
		this.addDefault("Error.InvalidNumber", "%VALUE% is not a valid number.");

		this.addDefault("Error.BlockTypes.Invalid", "Invalid Block Type.");
		this.addDefault("Error.BlockTypes.AlreadyExists", "%MATERIAL% is already a %TYPE% block.");
		this.addDefault("Error.BlockTypes.SpecifyAmount", "Invalid Syntax: /carz add %TYPE% (amount)");

		this.options().copyDefaults(true);
	}

}