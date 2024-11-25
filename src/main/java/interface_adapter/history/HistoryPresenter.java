package interface_adapter.history;

import use_case.HistoryNumber.HistoryOutputBoundary;
import use_case.HistoryNumber.HistoryOutputData;

public class HistoryPresenter implements HistoryOutputBoundary {
    private HistoryViewModel viewModel;

    public HistoryPresenter() {this.viewModel = new HistoryViewModel();}

    @Override
    public void present(HistoryOutputData outputData){

        this.viewModel.setCv(outputData.getCv());
        this.viewModel.setResume(outputData.getResume());
        this.viewModel.setSuggestion(outputData.getSuggestion());
    }

    public HistoryViewModel getViewModel() {return viewModel;}
}
