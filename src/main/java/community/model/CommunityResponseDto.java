package community.model;

import java.sql.Timestamp;

public class CommunityResponseDto {
	private int code;
	private String userId;
	private String userNickname;
	private String title;
	private String content;
	private String category;
	private Timestamp regDate;

	public CommunityResponseDto(int code, String userId, String userNickname, String title, String content,
			String category, Timestamp regDate) {
		super();
		this.code = code;
		this.userId = userId;
		this.userNickname = userNickname;
		this.title = title;
		this.content = content;
		this.category = category;
		this.regDate = regDate;
	}
	
	public CommunityResponseDto(int code, String userId, String userNickname, String title, String content,
			String category) {
		super();
		this.code = code;
		this.userId = userId;
		this.userNickname = userNickname;
		this.title = title;
		this.content = content;
		this.category = category;
	}

	public CommunityResponseDto(int code, String userNickname, String title, String content) {
		super();
		this.code = code;
		this.userNickname = userNickname;
		this.title = title;
		this.content = content;
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

	public void setCode(int code) {
		this.code = code;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	
	
}
