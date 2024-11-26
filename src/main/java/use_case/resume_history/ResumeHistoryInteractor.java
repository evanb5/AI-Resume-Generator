package use_case.resume_history;

import data_access.UserDataAccessInterface;
import entity.User;

public class ResumeHistoryInteractor implements ResumeHistoryInputBoundary {
    private final UserDataAccessInterface userDataAccess;
    private final ResumeHistoryOutputBoundary presenter;

    public ResumeHistoryInteractor(UserDataAccessInterface userDataAccess, ResumeHistoryOutputBoundary presenter) {
        this.userDataAccess = userDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void fetchResumeHistory(ResumeHistoryInputData inputData) {
        User user = userDataAccess.getCurrentUser();

        if (user == null) {
            // Handle case where no user is logged in
            ResumeHistoryOutputData outputData = new ResumeHistoryOutputData(
                    null,
                    "",
                    "No user is currently logged in."
            );
            presenter.present(outputData);
            return;
        }

        int index = inputData.getIndex();
        if (index == -4) {
            // Fetch all resume titles
            ResumeHistoryOutputData outputData = new ResumeHistoryOutputData(
                    user.getResumeTitles(),
                    "",
                    "Resume titles fetched successfully."
            );
            presenter.present(outputData);
        } else if (index >= 0 && index < user.getResumes().size()) {
            // Fetch specific resume content
            String resumeContent = user.getResumes().get(index);
            ResumeHistoryOutputData outputData = new ResumeHistoryOutputData(
                    user.getResumeTitles(),
                    resumeContent,
                    "Resume content fetched successfully."
            );
            presenter.present(outputData);
        } else {
            // Handle invalid index
            ResumeHistoryOutputData outputData = new ResumeHistoryOutputData(
                    user.getResumeTitles(),
                    "",
                    "Invalid resume index."
            );
            presenter.present(outputData);
        }
    }
}
