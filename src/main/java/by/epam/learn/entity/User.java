package by.epam.learn.entity;

/**
 * The {@code User} class describes the entity user
 * 
 * @author Ihar Klepcha
 * @see Entity
 */
public class User extends Entity {
	private static final long serialVersionUID = 1L;
	private long userId;
	private String login;
	private String name;
	private String email;
	private String phone;
	private UserRole role;
	private UserStatus status;
	
	/**
	 * Constructs a new user
	 */
	public User() {
	}

	/**
	 * Constructs a new user with the specified
	 * 
	 * @param userId {@link long} user id
	 * @param login {@link String} login
	 * @param name {@link String} name
	 * @param email {@link String} email address
	 * @param phone {@link String} phone number
	 * @param role {@link UserRole} role this user
	 * @param status {@link UserStatus} status this user
	 */
	public User(long userId, String login, String name, String email, String phone, UserRole role, 
			UserStatus status) {
		this.userId = userId;
		this.login = login;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.status = status;
	}
	
	/**
	 * Constructs a new user with the specified
	 * 
	 * @param login {@link String} login
	 * @param name {@link String} name
	 * @param email {@link String} email address
	 * @param role {@link UserRole} role this user
	 * @param status {@link UserStatus} status this user
	 */
	public User(String login, String name, String email, UserRole role, UserStatus status) {
		this.login = login;
		this.name = name;
		this.email = email;
		this.role = role;
		this.status = status;
	}

	/**
	 * Constructs a new user with the specified
	 * 
	 * @param login {@link String} login
	 * @param name {@link String} name
	 * @param email {@link String} email address
	 * @param phone {@link String} phone number
	 * @param role {@link UserRole} role this user
	 * @param status {@link UserStatus} status this user
	 */
	public User(String login, String name, String email, String phone, UserRole role, UserStatus status) {
		this.login = login;
		this.name = name;
		this.email = email;
		this.phone = phone;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (role != other.role)
			return false;
		if (status != other.status)
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
		sb.append(", role = ").append(role.toString());
		sb.append(", status = ").append(status.toString()).append(" }");
		return sb.toString();
	}
}
