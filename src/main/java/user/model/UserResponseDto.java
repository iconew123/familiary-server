package user.model;

public class UserResponseDto {
	private String id;
	private String nickname;
	private String name;
	private String security_number;
	private String telecom;
	private String phone;
	private String adress;
	private String email;

	public UserResponseDto(String id, String name, String email, String nickname, String adress, String security_number,
			String telecom, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.nickname = nickname;
		this.adress = adress;
		this.security_number = security_number;
		this.telecom = telecom;
		this.phone = phone;

	}

	public UserResponseDto(User user) {
		this.id = user.getId();
		this.name = getName();
		this.email = user.getEmail();
		this.nickname = getNickname();
		this.adress = getAdress();
		this.security_number = getSecurity_number();
		this.telecom = getTelecom();
		this.phone = getPhone();

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getSecurity_number() {
		return security_number;
	}

	public void setSecurity_number(String security_number) {
		this.security_number = security_number;
	}

	public String getTelecom() {
		return telecom;
	}

	public void setTelecom(String telecom) {
		this.telecom = telecom;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
