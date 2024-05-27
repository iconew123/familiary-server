package communityComment.model;

import java.sql.Timestamp;

public class CommunityCommentRequestDto {
	private int communityCode;
	private String userId;
	private String userNickname;
	private String content;
	private Timestamp regDate;

	public CommunityCommentRequestDto() {

	}

	public CommunityCommentRequestDto(int communityCode, String userId, String userNickname, String content,
			Timestamp regDate) {
		super();
		this.communityCode = communityCode;
		this.userId = userId;
		this.userNickname = userNickname;
		this.content = content;
		this.regDate = regDate;
	}

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
