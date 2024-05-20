package diary.model;

import java.sql.Blob;
import java.sql.Date;

public class DiaryResponseDto {
	private String babyCode;
	private Date date;
	private String title;
	private String content;
	private Blob photo;
	private String category;
	private String comment;
	
	public DiaryResponseDto(String babyCode, Date date, String title, String content, Blob photo, String category,
			String comment) {
		super();
		this.babyCode = babyCode;
		this.date = date;
		this.title = title;
		this.content = content;
		this.photo = photo;
		this.category = category;
		this.comment = comment;
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

	public Blob getPhoto() {
		return photo;
	}

	public String getCategory() {
		return category;
	}

	public String getComment() {
		return comment;
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

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
