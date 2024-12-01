package entity;

public class CommonCV implements CV {
    private String cv;
    private String name;

    @Override
    public String getCv() {
        return cv;
    }

    @Override
    public void setCv(String cv) {
        this.cv = cv;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
