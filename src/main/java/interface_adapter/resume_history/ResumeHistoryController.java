package interface_adapter.resume_history;

import use_case.resume_history.ResumeHistoryInputBoundary;
import use_case.resume_history.ResumeHistoryInputData;

public class ResumeHistoryController {
    private ResumeHistoryInputBoundary interactor;

    public ResumeHistoryController(ResumeHistoryInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void fetchResumeHistory(ResumeHistoryInputData inputData) {
        interactor.fetchResumeHistory(inputData);
    }
}
