package user.model;

public class UserRequestDto {

	private String id;
	private String password;
	private String nickname;
	private String name;
	private String securityNumber;
	private String telecom;
	private String phone;
	private String address;
	private String email;

	public UserRequestDto() {

	}

	public UserRequestDto(String id, String password, String nickname,
						  String telecom, String phone,String address, String email) {
		super();
		this.id = id;
		this.password = password;
		this.nickname = nickname;
		this.telecom = telecom;
		this.phone = phone;
		this.address = address;
		this.email = email;
	}

	public UserRequestDto(String id, String password, String nickname, String name, String securityNumber,
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

	public String getSecurityNumber() {
		return securityNumber;
	}

	public void setSecurityNumber(String securityNumber) {
		this.securityNumber = securityNumber;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
