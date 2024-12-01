package interface_adapter.resume_history;

import use_case.resume_history.ResumeHistoryInputBoundary;
import use_case.resume_history.ResumeHistoryInputData;

public class ResumeHistoryController {
    private final ResumeHistoryInputBoundary interactor;

    public ResumeHistoryController(ResumeHistoryInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void fetchResumeHistory(ResumeHistoryInputData inputData) {
        System.out.println("Controller fetchResumeHistory called with inputData: " + inputData.getIndex());
        interactor.fetchResumeHistory(inputData);
    }
}
