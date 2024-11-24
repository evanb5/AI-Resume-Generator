package interface_adapter.history;

import entity.User;

public class HistoryViewModel {
    private int cv;
    private int resume;
    private int suggestion;
    private User user;

    public void setCv(int cv) {this.cv = cv;}

    public int getCv() {return cv;}

    public void setResume(int resume) {this.resume = resume;}

    public int getResume() {return resume;}

    public void setSuggestion(int suggestion) {this.suggestion = suggestion;}

    public int getSuggestion() {return suggestion;}
}
