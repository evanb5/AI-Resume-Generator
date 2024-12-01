package use_case.resume_history;

import interface_adapter.resume_history.ResumeHistoryViewModel;

public interface ResumeHistoryOutputBoundary {
    void present(ResumeHistoryOutputData outputData);

    void present(ResumeHistoryOutputData outputData, ResumeHistoryViewModel viewModel);
}
