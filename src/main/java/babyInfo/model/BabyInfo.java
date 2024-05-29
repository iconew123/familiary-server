package babyInfo.model;

import java.sql.Timestamp;
import java.util.Date;

public class BabyInfo {
    private String baby_code;
    private Date date;
    private int height;
    private int weight;
    private String spec_note;
    private Timestamp reg_date;
    private Timestamp mode_date;

    public BabyInfo(String baby_code, Date date, int height, int weight, String spec_note, Timestamp reg_date, Timestamp mode_date) {
        super();

        this.baby_code = baby_code;
        this.date = date;
        this.height = height;
        this.weight = weight;
        this.spec_note = spec_note;
        this.reg_date = reg_date;
        this.mode_date = mode_date;

    }

    public String getBaby_code() {
        return baby_code;
    }

    public Date getDate() {
        return date;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getSpec_note() {
        return spec_note;
    }

    public Timestamp getReg_date() {
        return reg_date;
    }

    public Timestamp getMode_date() {
        return mode_date;
    }
}
