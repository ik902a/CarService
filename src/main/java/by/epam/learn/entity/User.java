package by.epam.learn.entity;

public class User extends Entity {//TODO set get equals
	private static final long serialVersionUID = 1L;
	private long userId;
	private String login;
	private String name;
	private String email;

	private int phone;
	private String role;
	private String status;
	
	public User() {
	}
	
	public User(long userId, String login, String name, String email, int phone, String role, String status) {
		this.userId = userId;
		this.login = login;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.status = status;
	}
	
	public User(String login, String name, String email, String role, String status) {
		this.login = login;
		this.name = name;
		this.email = email;
		this.role = role;
		this.status = status;
	}
	
	
	

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());

		result = prime * result + ((name == null) ? 0 : name.hashCode());

		result = prime * result + phone;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;

		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;

		if (phone != other.phone)
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nUser{ user ID = ").append(userId);
		sb.append(", login = ").append(login);
		sb.append(", name = ").append(name);
		sb.append(", email = ").append(email);
		sb.append(", phone = ").append(phone);
		sb.append(", role = ").append(role);
		sb.append(", status = ").append(status).append(" }");
		return sb.toString();
	}
}
