package interface_adapter.resume_history;

import use_case.resume_history.*;

public class ResumeHistoryPresenter implements ResumeHistoryOutputBoundary {
    private ResumeHistoryViewModel viewModel;

    public ResumeHistoryPresenter(ResumeHistoryViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(ResumeHistoryOutputData outputData) {
        final ResumeHistoryState state = viewModel.getState();
        state.setMessage(outputData.getMessage());
        state.setResumes(outputData.getResumes());
        state.setResumeContent(outputData.getResumeContent());
        viewModel.setState(state);
        viewModel.firePropertyChanged();

        viewModel.setResumes(outputData.getResumes());
        viewModel.setResumeContent(outputData.getResumeContent());
        viewModel.setMessage(outputData.getMessage());
    }

    public ResumeHistoryViewModel getViewModel() {
        return viewModel;
    }
}
