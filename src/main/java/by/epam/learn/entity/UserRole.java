package by.epam.learn.entity;

public enum UserRole {
	ADMIN("admin"), 
	MANAGER("manager"), 
	CLIENT("client"), 
	MECHANIC("mechanic"), 
	GUEST("guest");
	
	private String value;
	
	UserRole(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
