package interface_adapter.resume_history;

import use_case.resume_history.ResumeHistoryOutputBoundary;
import use_case.resume_history.ResumeHistoryOutputData;

public class ResumeHistoryPresenter implements ResumeHistoryOutputBoundary {
    private final ResumeHistoryViewModel viewModel;

    public ResumeHistoryPresenter(ResumeHistoryViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(ResumeHistoryOutputData outputData) {
        System.out.println("Presenter received output data: " + outputData.getResumes());
        viewModel.setResumes(outputData.getResumes());
        viewModel.setResumeContent(outputData.getResumeContent());
        viewModel.setMessage(outputData.getMessage());
    }

    public ResumeHistoryViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void present(ResumeHistoryOutputData outputData, ResumeHistoryViewModel viewModel) {
        // Not used, but required by the interface
    }
}
