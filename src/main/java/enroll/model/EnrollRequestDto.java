package enroll.model;


public class EnrollRequestDto {
	private String user_id;
	private String baby_code;
	private String position;
	
	public EnrollRequestDto(String user_id, String baby_code, String position) {
		super();
		
		this.user_id = user_id;
		this.baby_code = baby_code;
		this.position = position;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getBaby_code() {
		return baby_code;
	}

	public void setBaby_code(String baby_code) {
		this.baby_code = baby_code;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	
}
