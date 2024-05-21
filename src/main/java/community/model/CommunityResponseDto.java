package community.model;

import java.sql.Blob;
import java.sql.Timestamp;

public class CommunityResponseDto {
	private int code;
	private String userId;
	private String userNickname;
	private String title;
	private String content;
	private Blob photo;
	private String tag;
	private String comment;
	private Timestamp regDate;

	public CommunityResponseDto(int code, String userId, String userNickname, String title, String content, Blob photo,
			String tag, String comment, Timestamp regDate) {
		super();
		this.code = code;
		this.userId = userId;
		this.userNickname = userNickname;
		this.title = title;
		this.content = content;
		this.photo = photo;
		this.tag = tag;
		this.comment = comment;
		this.regDate = regDate;
	}

	public CommunityResponseDto(int code, String userId, String userNickname, String title, String content, Blob photo,
			String tag, String comment) {
		super();
		this.code = code;
		this.userId = userId;
		this.userNickname = userNickname;
		this.title = title;
		this.content = content;
		this.photo = photo;
		this.tag = tag;
		this.comment = comment;
	}
	
	public CommunityResponseDto(int code, String userNickname, String title, String content, Blob photo) {
		super();
		this.code = code;
		this.userNickname = userNickname;
		this.title = title;
		this.content = content;
		this.photo = photo;
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

	public Blob getPhoto() {
		return photo;
	}

	public String getTag() {
		return tag;
	}

	public String getComment() {
		return comment;
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

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

}
