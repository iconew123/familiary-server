package babyInfo.model;


public class BabyInfoResponseDto {
    private String baby_code;
    private String date;
    private int height;
    private int weight;
    private String spec_note;

    public BabyInfoResponseDto(String baby_code, int height, int weight, String spec_note) {
        super();

        this.baby_code = baby_code;
        this.height = height;
        this.weight = weight;

    }

    public String getBaby_code() {
        return baby_code;
    }

    public void setBaby_code(String baby_code) {
        this.baby_code = baby_code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getSpec_note() {
        return spec_note;
    }

    public void setSpec_note(String spec_note) {
        this.spec_note = spec_note;
    }
}
