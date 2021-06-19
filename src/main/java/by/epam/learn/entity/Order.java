package by.epam.learn.entity;

import java.time.LocalDate;

/**
 * The {@code Order} class describes the entity order
 * 
 * @author Ihar Klepcha
 * @see Entity
 */
public class Order extends Entity {
	private static final long serialVersionUID = 1L;
	private long orderId;
	private Car car;
	private WorkType workType;
	private String message;
	private OrderStatus status;
	private LocalDate date;
	private long mechanicId;
	
	/**
	 * Constructs a new order
	 */
	public Order() {
	}

	/**
	 * Constructs a new order with the specified
	 * 
	 * @param orderId {@link long} order id
	 * @param car {@link Car} car
	 * @param workType {@link WorkType} type of work
	 * @param message {@link String} message
	 * @param orderStatus {@link OrderStatus} status this order
	 * @param date {@link LocalDate} date
	 * @param mechanicId {@link long} mechanic for this order
	 */
	public Order(long orderId, Car car, WorkType workType, String message, OrderStatus status, LocalDate date,
			long mechanicId) {
		this.orderId = orderId;
		this.car = car;
		this.workType = workType;
		this.message = message;
		this.status = status;
		this.date = date;
		this.mechanicId = mechanicId;
	}
	
	/**
	 * Constructs a new order with the specified
	 * 
	 * @param car {@link Car} car
	 * @param workType {@link WorkType} type of work
	 * @param message {@link String} message
	 * @param orderStatus {@link OrderStatus} status this order
	 * @param date {@link LocalDate} date
	 */
	public Order(Car car, WorkType workType, String message, OrderStatus status, LocalDate date) {
		this.car = car;
		this.workType = workType;
		this.message = message;
		this.status = status;
		this.date = date;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public WorkType getWorkType() {
		return workType;
	}

	public void setWorkType(WorkType workType) {
		this.workType = workType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public long getMechanicId() {
		return mechanicId;
	}

	public void setMechanicId(long mechanicId) {
		this.mechanicId = mechanicId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (mechanicId ^ (mechanicId >>> 32));
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Order other = (Order) obj;
		if (car == null) {
			if (other.car != null)
				return false;
		} else if (!car.equals(other.car))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (mechanicId != other.mechanicId)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (orderId != other.orderId)
			return false;
		if (status != other.status)
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
		sb.append("\nOrder{ order id = ").append(orderId);
		sb.append(", vin car = ").append(car.getVin());
		sb.append(", work type = ").append(workType.getWorkType());
		sb.append(", message = ").append(message);
		sb.append(", status =  ").append(status.toString());
		sb.append(", date = ").append(date).append(" }");
		return sb.toString();
	}
}
