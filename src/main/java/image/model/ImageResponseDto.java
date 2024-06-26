package image.model;

import java.sql.Timestamp;


public class ImageResponseDto {

    private int num;
    private String url;
    private String id;
    private String type;
    private boolean status;
    private String code;
    private Timestamp regDate;
    private Timestamp modDate;

    public ImageResponseDto(String code, String url, String type, Timestamp regDate) {
        super();
        this.code = code;
        this.url = url;
        this.type = type;
        this.regDate = regDate;
    }

    public ImageResponseDto(String url, String id, String type, boolean status, String code) {
        this.url = url;
        this.id = id;
        this.type = type;
        this.status = status;
        this.code = code;
    }

    public ImageResponseDto(String url, String id, String type, boolean status, Timestamp regDate, Timestamp modDate, String code) {
        this.url = url;
        this.type = type;
        this.id = id;
        this.status = status;
        this.regDate = regDate;
        this.code = code;
        this.modDate = modDate;
    }

    public ImageResponseDto(int num, String url, String id, String type, String code, boolean status, Timestamp regDate, Timestamp modDate) {
        this.num = num;
        this.url = url;
        this.id = id;
        this.type = type;
        this.code = code;
        this.status = status;
        this.regDate = regDate;
        this.modDate = modDate;
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

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }

    public Timestamp getModDate() {
        return modDate;
    }

    public void setModDate(Timestamp modDate) {
        this.modDate = modDate;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
