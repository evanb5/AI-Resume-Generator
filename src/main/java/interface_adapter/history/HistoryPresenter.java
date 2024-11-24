package interface_adapter.history;

import use_case.HistoryNumber.HistoryOutputBoundary;
import use_case.HistoryNumber.HistoryOutputData;

public class HistoryPresenter implements HistoryOutputBoundary {
    private HistoryViewModel viewModel;

    public HistoryPresenter(HistoryViewModel viewModel) {this.viewModel = new HistoryViewModel();}

    @Override
    public void present(HistoryOutputData outputData){
        viewModel.setCv(outputData.getCv());
        viewModel.setResume(outputData.getResume());
        viewModel.setSuggestion(outputData.getSuggestion());
    }

    public HistoryViewModel getViewModel() {return viewModel;}
}
