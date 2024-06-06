package diaryComment.model;

public class DiaryCommentRequestDto {

	private String code;
	private int diaryCode;
	private String userId;
	private String nickName;
	private String content;

	public DiaryCommentRequestDto(String code, int diaryCode, String userId, String nickName, String content) {
		super();
		this.code = code;
		this.diaryCode = diaryCode;
		this.userId = userId;
		this.nickName = nickName;
		this.content = content;
	}

	public DiaryCommentRequestDto(int diaryCode, String userId, String content, String nickName) {
		this.diaryCode = diaryCode;
		this.userId = userId;
		this.content = content;
		this.nickName = nickName;
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

}
