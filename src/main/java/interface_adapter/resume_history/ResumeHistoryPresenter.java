package interface_adapter.resume_history;

import use_case.resume_history.*;

public class ResumeHistoryPresenter implements ResumeHistoryOutputBoundary {
    private ResumeHistoryViewModel viewModel;

    public ResumeHistoryPresenter() {
        this.viewModel = new ResumeHistoryViewModel();
    }

    @Override
    public void present(ResumeHistoryOutputData outputData) {
        viewModel.setResumeTitles(outputData.getResumeTitles());
        viewModel.setResumeContent(outputData.getResumeContent());
        viewModel.setMessage(outputData.getMessage());
    }

    public ResumeHistoryViewModel getViewModel() {
        return viewModel;
    }
}
