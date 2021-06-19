package by.epam.learn.entity;

/**
 * The {@code Price} class describes the entity price
 * 
 * @author Ihar Klepcha
 * @see Entity
 */
public class Price extends Entity {
	private static final long serialVersionUID = 1L;
	private long priceId;
	private String operation;
	private double price;
	private WorkType workType;
	
	/**
	 * Constructs a new price
	 */
	public Price() {
	}
	
	/**
	 * Constructs a new price with the specified
	 * 
	 * @param priceId {@link long} price id
	 * @param operation {@link String} operation description
	 * @param price {@link double} price
	 * @param workType {@link WorkType} type of work
	 */
	public Price(long priceId, String operation, double price, WorkType workType) {
		this.priceId = priceId;
		this.operation = operation;
		this.price = price;
		this.workType = workType;
	}
	
	/**
	 * Constructs a new price with the specified
	 * 
	 * @param operation {@link String} operation description
	 * @param price {@link double} price
	 * @param workType {@link WorkType} type of work
	 */
	public Price(String operation, double price, WorkType workType) {
		this.operation = operation;
		this.price = price;
		this.workType = workType;
	}

	public long getPriceId() {
		return priceId;
	}

	public void setPriceId(long priceId) {
		this.priceId = priceId;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public WorkType getWorkType() {
		return workType;
	}

	public void setWorkType(WorkType workType) {
		this.workType = workType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((operation == null) ? 0 : operation.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (priceId ^ (priceId >>> 32));
		result = prime * result + ((workType == null) ? 0 : workType.hashCode());
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
		Price other = (Price) obj;
		if (operation == null) {
			if (other.operation != null)
				return false;
		} else if (!operation.equals(other.operation))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (priceId != other.priceId)
			return false;
		if (workType == null) {
			if (other.workType != null)
				return false;
		} else if (!workType.equals(other.workType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nPrice{ price id = ").append(priceId);
		sb.append(", operation = ").append(operation);
		sb.append(", price = ").append(price);
		sb.append(", work type = ").append(workType.toString()).append(" }");
		return sb.toString();
	}
}
