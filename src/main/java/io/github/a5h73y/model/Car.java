package io.github.a5h73y.model;

import io.github.a5h73y.Carz;
import io.github.a5h73y.controllers.CarController;
import io.github.a5h73y.utility.StringUtils;

public class Car {

	private int entityId;

	private CarDetails carDetails;

	private double maxSpeed;

	private double currentSpeed;

	private double currentFuel;

	public Car(final int entityId) {
		this(entityId, null);
	}

	public Car(final int entityId, final String carType) {
		this.entityId = entityId;
		this.currentSpeed = 0.0;

		this.carDetails = Carz.getInstance().getCarController().getCarTypes().get(
				carType == null ? CarController.DEFAULT_CAR : carType);
		this.currentFuel = Carz.getInstance().getFuelController().getStartAmount();
		this.maxSpeed = this.carDetails.getStartMaxSpeed();
	}

	public void accelerate() {
		if (this.currentSpeed < this.maxSpeed) {
			this.currentSpeed += this.carDetails.getAcceleration();
		}
		this.currentFuel -= this.carDetails.getFuelUsage();
	}

	public void setCurrentFuel(double fuelAmount) {
		this.currentFuel = fuelAmount;
	}

	public boolean isFuelConsumed() {
		return getCurrentFuel() <= 0;
	}

	public void resetSpeed() {
		this.currentSpeed = 0.0;
	}

	@Override
	public String toString() {
		return StringUtils.getStandardHeading("Car Details") +
				"\nentityId = " + entityId +
				", \nmaxSpeed = " + maxSpeed +
				", \ncurrentSpeed = " + currentSpeed +
				", \ncurrentFuel = " + currentFuel +
				"\n" + carDetails;
	}

	public int getEntityId() {
		return entityId;
	}

	public Double getCurrentFuel() {
		return currentFuel;
	}

	public Double getCurrentSpeed() {
		return currentSpeed;
	}

	public CarDetails getCarDetails() {
		return carDetails;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
}
