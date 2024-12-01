package interface_adapter.CVhistory;

import use_case.CVhistory.CVhistoryOutputBoundary;
import use_case.CVhistory.CVhistoryOutputData;

public class CVhistoryPresenter implements CVhistoryOutputBoundary {
    private CVhistoryViewModel viewModel;

    public CVhistoryPresenter(CVhistoryViewModel viewModel) {this.viewModel = viewModel;}

    @Override
    public void present(CVhistoryOutputData outputData){
        final CVhistoryState state = viewModel.getState();
        state.setHistoryCV(outputData.getCVhistory());
        state.setTitles(outputData.getTitles());
        viewModel.setState(state);
        viewModel.firePropertyChanged();

        viewModel.setHistoryCV(outputData.getCVhistory());
        viewModel.setTitles(outputData.getTitles());
    }

    public CVhistoryViewModel getViewModel() {return viewModel;}
}
