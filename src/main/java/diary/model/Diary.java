package diary.model;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;

public class Diary {

	private int code;
	private String babyCode;
	private Date date;
	private String title;
	private String content;
	private String category;
	private Timestamp regDate;
	private Timestamp modDate;

	public Diary(int code, String babyCode, Date date, String title, String content, String category, Timestamp regDate,
			Timestamp modDate) {
		super();
		this.code = code;
		this.babyCode = babyCode;
		this.date = date;
		this.title = title;
		this.content = content;
		this.category = category;
		this.regDate = regDate;
		this.modDate = modDate;
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

	public Timestamp getRegDate() {
		return regDate;
	}

	public Timestamp getModDate() {
		return modDate;
	}

}
