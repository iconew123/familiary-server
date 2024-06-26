package community.model;

public class CommunityRequestDto {
	private int code;
	private String userId;
	private String userNickname;
	private String title;
	private String content;
	private String category;

	public CommunityRequestDto() {

	}
	
	public CommunityRequestDto(int code, String userId, String userNickname, String title, String content,
			String category) {
		super();
		this.code = code;
		this.userId = userId;
		this.userNickname = userNickname;
		this.title = title;
		this.content = content;
		this.category = category;
	}

	public CommunityRequestDto(int code, String userNickname, String title, String content,
							   String category) {
		super();
		this.code = code;
		this.userNickname = userNickname;
		this.title = title;
		this.content = content;
		this.category = category;
	}
	
	public CommunityRequestDto(String userId, String userNickname, String title, String content, String category) {
		super();
		this.userId = userId;
		this.userNickname = userNickname;
		this.title = title;
		this.content = content;
		this.category = category;
	}
	
	public CommunityRequestDto(int code) {
		super();
		this.code = code;
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

}
