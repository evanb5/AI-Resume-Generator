package use_case.HistoryNumber;

import data_access.UserDataAccessInterface;
import entity.User;

public class HistoryInteractor implements HistoryInputBoundary{
    private UserDataAccessInterface userDataAccess;
    private HistoryOutputBoundary presenter;

    public HistoryInteractor(UserDataAccessInterface userDataAccess, HistoryOutputBoundary presenter) {
        this.userDataAccess = userDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void historyinput() {
        User user = userDataAccess.getCurrentUser();
        HistoryOutputData outputData;

        if (user != null) {
            String username = user.getUsername(); // Access only if user is not null
            outputData = new HistoryOutputData(userDataAccess.getCvCount(username), userDataAccess.getResumeCount(username));
        } else {
            outputData = new HistoryOutputData(0, 0);
        }

        this.presenter.present(outputData);
    }

}
