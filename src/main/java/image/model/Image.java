package image.model;

import java.sql.Timestamp;

public class Image {
    private int num;
    private String url;
    private String id;
    private String type;
    private boolean status;
    private String code;
    private Timestamp regDate;
    private Timestamp modDate;

    public Image(int num, String url, String id, String type, boolean status, Timestamp regDate, String code, Timestamp modDate) {
        this.num = num;
        this.url = url;
        this.id = id;
        this.type = type;
        this.status = status;
        this.regDate = regDate;
        this.code = code;
        this.modDate = modDate;
    }

    public int getNum() {
        return num;
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

    public boolean isStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public Timestamp getModDate() {
        return modDate;
    }
}
