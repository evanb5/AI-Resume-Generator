package use_case.HistoryNumber;

import entity.User;

public class HistoryOutputData {
    private int cv;
    private int resume;
    private int suggestion;
    private User user;

    public HistoryOutputData(int cv, int resume, int suggestion, User user) {
        this.cv = cv;
        this.resume = resume;
        this.suggestion = suggestion;
        this.user = user;
    }

    public int getCv() {return cv;}
    public int getResume() {return resume;}
    public int getSuggestion() {return suggestion;}
    public User getUser() {return user;}

}
