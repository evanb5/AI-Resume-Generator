package use_case.CVhistory;

import java.util.List;
import java.util.Map;

public class CVhistoryOutputData {
    private String CVhistory;
    private List<String> titles;

    public CVhistoryOutputData(String CVhistory, List<String> titles) {
        this.CVhistory = CVhistory;
        this.titles = titles;
    }

    public String getCVhistory() {return CVhistory;}
    public List<String> getTitles() {return titles;}
}
