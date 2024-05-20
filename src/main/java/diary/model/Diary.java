package diary.model;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;

public class Diary {
	private String babyCode;
	private Date date;
	private String title;
	private String content;
	private Blob photo;
	private String category;
	private String comment;
	private Timestamp regDate;
	private Timestamp modDate;
	
	public Diary(String babyCode, Date date, String title, String content, Blob photo, String category, String comment,
			Timestamp regDate, Timestamp modDate) {
		super();
		this.babyCode = babyCode;
		this.date = date;
		this.title = title;
		this.content = content;
		this.photo = photo;
		this.category = category;
		this.comment = comment;
		this.regDate = regDate;
		this.modDate = modDate;
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

	public Timestamp getRegDate() {
		return regDate;
	}

	public Timestamp getModDate() {
		return modDate;
	}
	
	
	
}
