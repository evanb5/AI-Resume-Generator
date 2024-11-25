package interface_adapter.CVhistory;

import java.util.List;

public class CVhistoryViewModel {
    private String historyCV;
    private List<String> titles;

    public String getHistoryCV() {return historyCV;}
    public void setHistoryCV(String historyCV) {this.historyCV = historyCV;}
    public List<String> getTitles() {return titles;}
    public void setTitles(List<String> titles) {this.titles = titles;}

}
