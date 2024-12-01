package interface_adapter.history;

import entity.User;

public class HistoryViewModel {
    private int cv;
    private int resume;

    public void setCv(int cv) {this.cv = cv;}

    public int getCv() {return this.cv;}

    public void setResume(int resume) {this.resume = resume;}

    public int getResume() {return this.resume;}

}
