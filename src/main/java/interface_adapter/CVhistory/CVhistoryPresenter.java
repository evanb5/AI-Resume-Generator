package interface_adapter.CVhistory;

import use_case.CVhistory.CVhistoryOutputBoundary;
import use_case.CVhistory.CVhistoryOutputData;

public class CVhistoryPresenter implements CVhistoryOutputBoundary {
    private CVhistoryViewModel viewModel;

    public CVhistoryPresenter() {this.viewModel = new CVhistoryViewModel();}

    @Override
    public void present(CVhistoryOutputData outputData){
        viewModel.setHistoryCV(outputData.getCVhistory());
        viewModel.setTitles(outputData.getTitles());
    }

    public CVhistoryViewModel getViewModel() {return viewModel;}
}
