package use_case.HistoryNumber;

public class HistoryOutputData {
    private int cv;
    private int resume;

    public HistoryOutputData(int cv, int resume) {
        this.cv = cv;
        this.resume = resume;
    }

    public int getCv() {return this.cv;}
    public int getResume() {return this.resume;}
}
