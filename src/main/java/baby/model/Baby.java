package baby.model;

import java.sql.Timestamp;

import com.mysql.cj.jdbc.Blob;


public class Baby {
	private String code;
	private String nickname;
	private String name;		// null 
	private String gender;		// null 
	private String expected_date;
	private Blob photo;			// null 
	private String blood_type;	// null 
	private Timestamp regDate;
	private Timestamp modDate;
	
	public Baby(String code, String nickname, String name, String gender,
			String expected_date, Blob photo, String blood_type, Timestamp regDate, Timestamp modDate) {
		super();
		
		
		this.code = code;
		this.nickname = nickname;
		this.name = name;
		this.gender = gender;
		this.expected_date = expected_date;
		this.photo = photo;
		this.blood_type = blood_type;
		this.regDate = regDate;
		this.modDate = modDate;
	}

	public String getCode() {
		return code;
	}

	public String getNickname() {
		return nickname;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String getExpected_date() {
		return expected_date;
	}

	public Blob getPhoto() {
		return photo;
	}

	public String getBlood_type() {
		return blood_type;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public Timestamp getModDate() {
		return modDate;
	}
	
	
}