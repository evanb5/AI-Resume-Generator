package use_case.resume_history;

public class ResumeHistoryInputData {
    private int index; // -4 for all resumes, -3 for next, -2 for previous, or specific index

    public ResumeHistoryInputData(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
