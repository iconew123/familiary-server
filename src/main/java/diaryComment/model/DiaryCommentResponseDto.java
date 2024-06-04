package diaryComment.model;

import java.sql.Timestamp;

public class DiaryCommentResponseDto {
	private String code; // 다이어리 댓글 번호
	private int diaryCode;
	private String userId;
	private String nickName;
	private String content;
	private Timestamp regDate;
	private Timestamp modDate;

	public DiaryCommentResponseDto(String code, int diaryCode, String userId, String nickName, String content,
			Timestamp regDate, Timestamp modDate) {
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

	public void setCode(String code) {
		this.code = code;
	}

	public void setDiaryCode(int diaryCode) {
		this.diaryCode = diaryCode;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public void setModDate(Timestamp modDate) {
		this.modDate = modDate;
	}

}
