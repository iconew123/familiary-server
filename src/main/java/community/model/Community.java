package community.model;

import java.sql.Timestamp;

public class Community {
	private int code;
	private String userId;
	private String userNickname;
	private String title;
	private String content;
	private String category;
	private Timestamp regDate;
	private Timestamp modDate;

	public Community(int code, String userId, String userNickname, String title, String content, String category,
			Timestamp regDate, Timestamp modDate) {
		super();
		this.code = code;
		this.userId = userId;
		this.userNickname = userNickname;
		this.title = title;
		this.content = content;
		this.category = category;
		this.regDate = regDate;
		this.modDate = modDate;
	}

	public int getCode() {
		return code;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getCategory() {
		return category;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public Timestamp getModDate() {
		return modDate;
	}

}