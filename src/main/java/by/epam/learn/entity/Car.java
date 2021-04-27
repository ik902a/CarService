package by.epam.learn.entity;

import java.time.LocalDate;

public class Car extends Entity {
	private static final long serialVersionUID = 1L;
	private long carId;
	private String vin;
	private String name;
	private String model;
	private LocalDate yearProduction;
	private String fuelType;
	private double volumeEngine;
	private String transmisionType;
	
	public Car() {
	}
	
	public Car(long carId, String vin, String name, String model, LocalDate yearСarProduction, String fuelType,
			double volumeEngine, String transmisionType) {
		this.carId = carId;
		this.vin = vin;
		this.name = name;
		this.model = model;
		this.yearProduction = yearСarProduction;
		this.fuelType = fuelType;
		this.volumeEngine = volumeEngine;
		this.transmisionType = transmisionType;
	}

	public long getCarId() {
		return carId;
	}

	public void setCarId(long carId) {
		this.carId = carId;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public LocalDate getYearСarProduction() {
		return yearProduction;
	}

	public void setYearСarProduction(LocalDate yearСarProduction) {
		this.yearProduction = yearСarProduction;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public double getVolumeEngine() {
		return volumeEngine;
	}

	public void setVolumeEngine(double volumeEngine) {
		this.volumeEngine = volumeEngine;
	}

	public String getTransmisionType() {
		return transmisionType;
	}

	public void setTransmisionType(String transmisionType) {
		this.transmisionType = transmisionType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (carId ^ (carId >>> 32));
		result = prime * result + ((fuelType == null) ? 0 : fuelType.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((transmisionType == null) ? 0 : transmisionType.hashCode());
		result = prime * result + ((vin == null) ? 0 : vin.hashCode());
		long temp;
		temp = Double.doubleToLongBits(volumeEngine);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((yearProduction == null) ? 0 : yearProduction.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (carId != other.carId)
			return false;
		if (fuelType == null) {
			if (other.fuelType != null)
				return false;
		} else if (!fuelType.equals(other.fuelType))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (transmisionType == null) {
			if (other.transmisionType != null)
				return false;
		} else if (!transmisionType.equals(other.transmisionType))
			return false;
		if (vin == null) {
			if (other.vin != null)
				return false;
		} else if (!vin.equals(other.vin))
			return false;
		if (Double.doubleToLongBits(volumeEngine) != Double.doubleToLongBits(other.volumeEngine))
			return false;
		if (yearProduction == null) {
			if (other.yearProduction != null)
				return false;
		} else if (!yearProduction.equals(other.yearProduction))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nCar{ Car ID = ").append(carId);
		sb.append(", vin = ").append(vin);
		sb.append(", name = ").append(name);
		sb.append(", model = ").append(model);
		sb.append(", year of production =  ").append(yearProduction);
		sb.append(", fuel type = ").append(fuelType);
		sb.append(", engine volume = ").append(volumeEngine);
		sb.append(", transmission type = ").append(transmisionType).append(" }");
		return sb.toString();
	}
}
