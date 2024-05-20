package user.model;

import java.sql.Timestamp;

public class User {

	private String id;
	private String baby_code;
	private String password;
	private String nickname;
	private String name;
	private String security_number;
	private String telecom;
	private String phone;
	private String adress;
	private String email;
	private String position;
	private String is_admin;
	private Timestamp regDate;
	private Timestamp modDate;

	public User(String id, String baby_code, String password, String nickname, String name, String security_number,
			String telecom, String phone, String adress, String email, String position, String is_admin,
			Timestamp regDate, Timestamp modDate) {
		super();
		this.id = id;
		this.baby_code = baby_code;
		this.password = password;
		this.nickname = nickname;
		this.name = name;
		this.security_number = security_number;
		this.telecom = telecom;
		this.phone = phone;
		this.adress = adress;
		this.email = email;
		this.position = position;
		this.is_admin = is_admin;
		this.regDate = regDate;
		this.modDate = modDate;
	}

	public String getId() {
		return id;
	}

	public String getBaby_code() {
		return baby_code;
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

	public String getSecurity_number() {
		return security_number;
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

	public String getPosition() {
		return position;
	}

	public String getIs_admin() {
		return is_admin;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public Timestamp getModDate() {
		return modDate;
	}

}
