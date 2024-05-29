package user.model;

public class UserResponseDto {
	private String id;
	private String nickname;
	private String name;
	private String securityNumber;
	private String telecom;
	private String phone;
	private String adress;
	private String email;

	public UserResponseDto(String id, String name, String email, String nickname, String adress, String securityNumber,
			String telecom, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.nickname = nickname;
		this.adress = adress;
		this.securityNumber = securityNumber;
		this.telecom = telecom;
		this.phone = phone;

	}

	public UserResponseDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.nickname = user.getNickname();
		this.adress = user.getAdress();
		this.securityNumber = user.getSecurityNumber();
		this.telecom = user.getTelecom();
		this.phone = user.getPhone();

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

	public String getSecurityNunber() {
		return securityNumber;
	}

	public void setSecurity_number(String securityNumber) {
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
}
