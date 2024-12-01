package interface_adapter.history;

import use_case.HistoryNumber.HistoryOutputBoundary;
import use_case.HistoryNumber.HistoryOutputData;

public class HistoryPresenter implements HistoryOutputBoundary {
    private HistoryViewModel viewModel;

    public HistoryPresenter(HistoryViewModel viewModel) {this.viewModel = viewModel;}

    @Override
    public void present(HistoryOutputData outputData){
        final HistoryState state = viewModel.getState();
        state.setCv(outputData.getCv());
        state.setResume(outputData.getResume());
        viewModel.setState(state);
        viewModel.firePropertyChanged();

        this.viewModel.setCv(outputData.getCv());
        this.viewModel.setResume(outputData.getResume());
    }

    public HistoryViewModel getViewModel() {return viewModel;}
}
