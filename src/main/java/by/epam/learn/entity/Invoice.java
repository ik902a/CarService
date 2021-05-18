package by.epam.learn.entity;

import java.time.LocalDate;

public class Invoice extends Entity {
	private static final long serialVersionUID = 1L;
	private long invoiceId;
	private User user;
	private Price price;
	private LocalDate date;
	
	public Invoice() {
	}

	public Invoice(long invoiceId, User user, Price price, LocalDate date) {
		this.invoiceId = invoiceId;
		this.user = user;
		this.price = price;
		this.date = date;
	}

	public Invoice(User user, Price price, LocalDate date) {
		this.user = user;
		this.price = price;
		this.date = date;
	}
	
	public long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (invoiceId ^ (invoiceId >>> 32));
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Invoice other = (Invoice) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (invoiceId != other.invoiceId)
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nInvoice{ Invoice ID = ").append(invoiceId);
		sb.append(", user =  ").append(user.getLogin());
		sb.append(", price{ ").append(price.toString());
		sb.append("}, date = ").append(date).append(" }");
		return sb.toString();
	}
}
