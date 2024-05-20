package baby.model;


import javax.sql.rowset.serial.SerialBlob;


public class BabyResponseDto {
	private String code;
	private String nickname;
	private String name;		// null 
	private String gender;		// null 
	private String expected_date;
	private SerialBlob photo;			// null 
	private String blood_type;	// null
	
	
	public BabyResponseDto(String code, String nickname, String name, String gender,
			String expected_date, SerialBlob photo, String blood_type) {
		super();
		
		
		this.code = code;
		this.nickname = nickname;
		this.name = name;
		this.gender = gender;
		this.expected_date = expected_date;
		this.photo = photo;
		this.blood_type = blood_type;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
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


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getExpected_date() {
		return expected_date;
	}


	public void setExpected_date(String expected_date) {
		this.expected_date = expected_date;
	}


	public SerialBlob getPhoto() {
		return photo;
	}


	public void setPhoto(SerialBlob photo) {
		this.photo = photo;
	}


	public String getBlood_type() {
		return blood_type;
	}

	public void setBlood_type(String blood_type) {
		this.blood_type = blood_type;
	}
	
	
}