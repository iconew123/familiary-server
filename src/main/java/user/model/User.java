package user.model;

import java.sql.Timestamp;

public class User {

	private String id;
	private String nickname;
	private String name;
	private String securityNumber;
	private String telecom;
	private String phone;
	private String adress;
	private String email;

	public User(String id, String nickname, String name, String securityNumber,
			String telecom, String phone, String adress, String email) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.name = name;
		this.securityNumber = securityNumber;
		this.telecom = telecom;
		this.phone = phone;
		this.adress = adress;
		this.email = email;
	}

	public String getId() {
		return id;
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

	public String getAdress() {
		return adress;
	}

	public String getEmail() {
		return email;
	}

}
