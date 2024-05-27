package communityComment.model;

import java.sql.Timestamp;

public class CommunityComment {
	private int communityCode;
	private String userId;
	private String userNickname;
	private String content;
	private Timestamp regDate;

	public CommunityComment(int communityCode, String userId, String userNickname, String content, Timestamp regDate) {
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

}
