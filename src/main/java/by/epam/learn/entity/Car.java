package by.epam.learn.entity;

public class Car extends Entity {
	private static final long serialVersionUID = 1L;
	private long carId;
	private User user;
	private String vin;
	private String brand;
	private String model;
	private String yearProduction;
	private String fuelType;
	private String volumeEngine;
	private String transmisionType;
	
	public Car() {
	}

	public Car(long carId, User user, String vin, String brand, String model, String yearProduction, String fuelType,
			String volumeEngine, String transmisionType) {
		this.carId = carId;
		this.user = user;
		this.vin = vin;
		this.brand = brand;
		this.model = model;
		this.yearProduction = yearProduction;
		this.fuelType = fuelType;
		this.volumeEngine = volumeEngine;
		this.transmisionType = transmisionType;
	}

	public Car(User user, String vin, String brand, String model, String yearProduction, String fuelType,
			String volumeEngine, String transmisionType) {
		this.user = user;
		this.vin = vin;
		this.brand = brand;
		this.model = model;
		this.yearProduction = yearProduction;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYearProduction() {
		return yearProduction;
	}

	public void setYearProduction(String yearProduction) {
		this.yearProduction = yearProduction;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getVolumeEngine() {
		return volumeEngine;
	}

	public void setVolumeEngine(String volumeEngine) {
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
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + (int) (carId ^ (carId >>> 32));
		result = prime * result + ((fuelType == null) ? 0 : fuelType.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((transmisionType == null) ? 0 : transmisionType.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((vin == null) ? 0 : vin.hashCode());
		result = prime * result + ((volumeEngine == null) ? 0 : volumeEngine.hashCode());
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
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
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
		if (transmisionType == null) {
			if (other.transmisionType != null)
				return false;
		} else if (!transmisionType.equals(other.transmisionType))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (vin == null) {
			if (other.vin != null)
				return false;
		} else if (!vin.equals(other.vin))
			return false;
		if (volumeEngine == null) {
			if (other.volumeEngine != null)
				return false;
		} else if (!volumeEngine.equals(other.volumeEngine))
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
		sb.append(", brand = ").append(brand);
		sb.append(", model = ").append(model);
		sb.append(", year of production =  ").append(yearProduction);
		sb.append(", fuel type = ").append(fuelType);
		sb.append(", engine volume = ").append(volumeEngine);
		sb.append(", transmission type = ").append(transmisionType).append(" }");
		return sb.toString();
	}
}
