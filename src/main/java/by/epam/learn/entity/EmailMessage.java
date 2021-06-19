package by.epam.learn.entity;

/**
 * The {@code EmailMessage} class describes the email
 * 
 * @author Ihar Klepcha
 * @see Entity
 */
public class EmailMessage {
	private String recipient;
	private String subject;
	private String message;

	/**
	 * Constructs a new email message
	 */
	public EmailMessage() {
	}
	
	/**
	 * Constructs a new email message with the specified
	 * 
	 * @param recipient {@link String} recipient a message
	 * @param subject {@link String} subject a message
	 * @param message {@link String} text a message
	 */
	public EmailMessage(String recipient, String subject, String message) {
		this.recipient = recipient;
		this.subject = subject;
		this.message = message;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((recipient == null) ? 0 : recipient.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		EmailMessage other = (EmailMessage) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (recipient == null) {
			if (other.recipient != null)
				return false;
		} else if (!recipient.equals(other.recipient))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nEmail message[ Recipient = ").append(recipient);
		sb.append(", subject = ").append(subject);
		sb.append(", messge = ").append(message).append(" ]");
		return sb.toString();
	}
}
