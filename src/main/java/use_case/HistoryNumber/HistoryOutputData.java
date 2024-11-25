package use_case.HistoryNumber;

public class HistoryOutputData {
    private int cv;
    private int resume;
    private int suggestion;

    public HistoryOutputData(int cv, int resume, int suggestion) {
        this.cv = cv;
        this.resume = resume;
        this.suggestion = suggestion;
    }

    public int getCv() {return this.cv;}
    public int getResume() {return this.resume;}
    public int getSuggestion() {return this.suggestion;}
}
