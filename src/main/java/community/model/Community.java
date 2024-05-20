package community.model;

import java.sql.Blob;
import java.sql.Timestamp;

public class Community {
	private int code;
	private String userId;
	private String userNickname;
	private String title;
	private String content;
	private Blob photo;
	private String tag;
	private String comment;
	private Timestamp regDate;
	private Timestamp modDate;

	public Community(int code, String userId, String userNickname, String title, String content, Blob photo,
			String tag, String comment, Timestamp regDate, Timestamp modDate) {
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

	public Timestamp getModDate() {
		return modDate;
	}

}
