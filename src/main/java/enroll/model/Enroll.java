package enroll.model;

import java.sql.Timestamp;

public class Enroll {
	private String user_id;
	private String baby_code;
	private String position;
	private Timestamp regDate;
	private Timestamp modDate;
	
	public Enroll(String user_id, String baby_code, String position, Timestamp regDate, Timestamp modeDate) {
		super();
		
		this.user_id = user_id;
		this.baby_code = baby_code;
		this.position = position;
		this.regDate = regDate;
		this.modDate = modeDate;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getBaby_code() {
		return baby_code;
	}

	public String getPosition() {
		return position;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public Timestamp getModDate() {
		return modDate;
	}
	
	
}
