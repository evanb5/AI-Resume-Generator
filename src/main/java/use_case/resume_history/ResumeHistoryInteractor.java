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
            System.out.println("No user is logged in.");
            ResumeHistoryOutputData outputData = new ResumeHistoryOutputData(
                    0,
                    "",
                    "No user is currently logged in."
            );
            presenter.present(outputData);
            return;
        }

        int index = inputData.getIndex();
        if (index == -4) {
            System.out.println("Fetching all resumes...");
            int resumeCount = userDataAccess.getResumeCount(user.getUsername());
            ResumeHistoryOutputData outputData = new ResumeHistoryOutputData(
                    resumeCount,
                    "",
                    "Resume titles fetched successfully."
            );
            presenter.present(outputData);
        } else if (index >= 0 && index < userDataAccess.getResumeCount(user.getUsername())) {
            System.out.println("Fetching specific resume at index: " + index);
            String resumeContent = userDataAccess.getResumeContent(user.getUsername(), index).toString();
            ResumeHistoryOutputData outputData = new ResumeHistoryOutputData(
                    userDataAccess.getResumeCount(user.getUsername()),
                    resumeContent,
                    "Resume content fetched successfully."
            );
            presenter.present(outputData);
        } else {
            System.out.println("Invalid index: " + index);
            ResumeHistoryOutputData outputData = new ResumeHistoryOutputData(
                    userDataAccess.getResumeCount(user.getUsername()),
                    "",
                    "Invalid resume index."
            );
            presenter.present(outputData);
        }
    }
}
