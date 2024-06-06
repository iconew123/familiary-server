package user.model;

public class User {

	private String id;
	private String password;
	private String nickname;
	private String name;
	private String securityNumber;
	private String telecom;
	private String phone;
	private String address;
	private String email;

	public User(String id, String password, String nickname, String name, String securityNumber,
				String telecom, String phone, String address, String email) {
		super();
		this.id = id;
		this.password = password;
		this.nickname = nickname;
		this.name = name;
		this.securityNumber = securityNumber;
		this.telecom = telecom;
		this.phone = phone;
		this.address = address;
		this.email = email;
	}

	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}


	public String getNickname() {
		return nickname;
	}

	public String getName() {
		return name;
	}

	public String getSecurityNumber() {
		return securityNumber;
	}

	public String getTelecom() {
		return telecom;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

}
