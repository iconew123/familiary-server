package image.model;

public class ImageRequestDto {
	
	private String url;
	private String id;
	private String type;
	private boolean status;
	private int code;
	
	public ImageRequestDto(String url, String id, String type, boolean status, int code) {
		super();
		this.url = url;
		this.id = id;
		this.type = type;
		this.status = status;
		this.code = code;
	}

	public ImageRequestDto(String url, String id, String type, int code) {
		super();
		this.url = url;
		this.id = id;
		this.type = type;
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public boolean getStatus() {
		return status;
	}

	public int getCode() {
		return code;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
	
}
