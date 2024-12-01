package interface_adapter.history;

import entity.User;
import interface_adapter.ViewModel;

public class HistoryViewModel extends ViewModel<HistoryState> {
    private int cv;
    private int resume;

    public HistoryViewModel() {
        setState(new HistoryState());
        cv = 0;
        resume = 0;
    }

    public void setCv(int cv) {this.cv = cv;}

    public int getCv() {return this.cv;}

    public void setResume(int resume) {this.resume = resume;}

    public int getResume() {return this.resume;}

}
