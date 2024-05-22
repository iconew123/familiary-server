package image.model;

import java.sql.Timestamp;

public class ImageResponseDto {
	private String code;
	private String url;
	private String type;
	private Timestamp regDate;
	
	public ImageResponseDto(String code, String url, String type, Timestamp regDate) {
		super();
		this.code = code;
		this.url = url;
		this.type = type;
		this.regDate = regDate;
	}

	public String getCode() {
		return code;
	}

	public String getUrl() {
		return url;
	}

	public String getType() {
		return type;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

}
