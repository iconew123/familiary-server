package image.model;

import java.sql.Timestamp;

public class ImageResponseDto {

	private String url;
	private String id;
	private String type;
	private boolean status;
	private String code;

	public ImageResponseDto(String url, String id, String type, boolean status, String code) {
		this.url = url;
		this.id = id;
		this.type = type;
		this.status = status;
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
