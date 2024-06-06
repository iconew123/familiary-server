package diaryComment.model;

import java.sql.Timestamp;

public class DiaryComment {
	private String code;
	private int diaryCode;
	private String userId;
	private String nickName;
	private String content;
	private Timestamp regDate;
	private Timestamp modDate;

	public DiaryComment(String code, int diaryCode, String userId, String nickName, String content, Timestamp regDate,
			Timestamp modDate) {
		super();
		this.code = code;
		this.diaryCode = diaryCode;
		this.userId = userId;
		this.nickName = nickName;
		this.content = content;
		this.regDate = regDate;
		this.modDate = modDate;
	}

	public String getCode() {
		return code;
	}

	public int getDiaryCode() {
		return diaryCode;
	}

	public String getUserId() {
		return userId;
	}

	public String getNickName() {
		return nickName;
	}

	public String getContent() {
		return content;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public Timestamp getModDate() {
		return modDate;
	}

}
