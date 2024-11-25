package interface_adapter.CVhistory;

import  use_case.CVhistory.*;

public class CVhistoryController {
    private CVhistoryInputBoundary interactor;

    public CVhistoryController(CVhistoryInputBoundary interactor) {this.interactor = interactor;}

    public void CVhistory(CVhistoryInputData inputData){interactor.CVhistory(inputData);}
}
