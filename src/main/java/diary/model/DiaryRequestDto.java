package diary.model;

import java.sql.Blob;
import java.sql.Date;

public class DiaryRequestDto {

	private int code;
	private String babyCode;
	private Date date;
	private String title;
	private String content;
	private String category;

	public DiaryRequestDto(int code, String babyCode, Date date, String title, String content, String category) {
		super();
		this.code = code;
		this.babyCode = babyCode;
		this.date = date;
		this.title = title;
		this.content = content;
		this.category = category;
	}
	
	
	
	public DiaryRequestDto(String babyCode, String title, String content, String category) {
		super();
		this.babyCode = babyCode;
		this.title = title;
		this.content = content;
		this.category = category;
	}



	public int getCode() {
		return code;
	}

	public String getBabyCode() {
		return babyCode;
	}

	public Date getDate() {
		return date;
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

	public void setBabyCode(String babyCode) {
		this.babyCode = babyCode;
	}

	public void setDate(Date date) {
		this.date = date;
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
