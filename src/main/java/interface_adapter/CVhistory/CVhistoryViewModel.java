package interface_adapter.CVhistory;

import interface_adapter.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class CVhistoryViewModel extends ViewModel<CVhistoryState> {
    private String historyCV;
    private List<String> titles;

    public CVhistoryViewModel() {
        setState(new CVhistoryState());
        historyCV = "";
        titles = new ArrayList<>();
    }

    public String getHistoryCV() {return historyCV;}
    public void setHistoryCV(String historyCV) {this.historyCV = historyCV;}
    public List<String> getTitles() {return titles;}
    public void setTitles(List<String> titles) {this.titles = titles;}

}
