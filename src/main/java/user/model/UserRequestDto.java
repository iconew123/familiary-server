package user.model;

public class UserRequestDto {

	private String id;
	private String password;
	private String nickname;
	private String name;
	private String security_number;
	private String telecom;
	private String phone;
	private String adress;
	private String email;
	private String position;

	public UserRequestDto() {

	}

	public UserRequestDto(String id, String password, String nickname, String name, String security_number,
			String telecom, String phone) {
		super();
		this.id = id;
		this.password = password;
		this.nickname = nickname;
		this.name = name;
		this.security_number = security_number;
		this.telecom = telecom;
		this.phone = phone;
	}

	public UserRequestDto(String id, String password, String nickname, String name, String security_number,
			String telecom, String phone, String adress, String email, String position) {
		super();
		this.id = id;
		this.password = password;
		this.nickname = nickname;
		this.name = name;
		this.security_number = security_number;
		this.telecom = telecom;
		this.phone = phone;
		this.adress = adress;
		this.email = email;
		this.position = position;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}


}
