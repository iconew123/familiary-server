package communityComment.model;

import java.sql.Timestamp;

public class CommunityCommentResponseDto {
	private String code;
	private int communityCode;
	private String userId;
	private String userNickname;
	private String content;
	private Timestamp regDate;

	public CommunityCommentResponseDto(String code, int communityCode, String userId, String userNickname, String content,
			Timestamp regDate) {
		super();
		this.code = code;
		this.communityCode = communityCode;
		this.userId = userId;
		this.userNickname = userNickname;
		this.content = content;
		this.regDate = regDate;
	}

	public CommunityCommentResponseDto(String code, String userNickname, String content) {
		super();
		this.code = code;
		this.userNickname = userNickname;
		this.content = content;
	}

	public String getCode() { return code; }

	public int getCommunityCode() {
		return communityCode;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public String getContent() {
		return content;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setCode(String code) { this.code = code; }

	public void setCommunityCode(int communityCode) {
		this.communityCode = communityCode;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

}
