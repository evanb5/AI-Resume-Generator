package interface_adapter.history;

public class HistoryViewModel {
    private int cv;
    private int resume;
    private int suggestion;

    public void setCv(int cv) {this.cv = cv;}

    public int getCv() {return this.cv;}

    public void setResume(int resume) {this.resume = resume;}

    public int getResume() {return this.resume;}

    public void setSuggestion(int suggestion) {this.suggestion = suggestion;}

    public int getSuggestion() {return this.suggestion;}
}
