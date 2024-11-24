package interface_adapter.history;

import use_case.HistoryNumber.*;

public class HistoryController {
    private HistoryInputBoundary interactor;

    public HistoryController(HistoryInputBoundary interactor) {this.interactor = interactor;}

    public void historyinput(){
        interactor.historyinput();
    }
}
