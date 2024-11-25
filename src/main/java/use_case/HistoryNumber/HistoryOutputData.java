package use_case.HistoryNumber;

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

    public int getCv() {return this.cv;}
    public int getResume() {return this.resume;}
    public int getSuggestion() {return this.suggestion;}
    public User getUser() {return this.user;}

}
