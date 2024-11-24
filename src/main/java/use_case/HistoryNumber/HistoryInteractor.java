package use_case.HistoryNumber;

import data_access.UserDataAccessInterface;
import entity.User;

public class HistoryInteractor{
    private UserDataAccessInterface userDataAccess;
    private HistoryOutputBoundary presenter;

    public HistoryInteractor(UserDataAccessInterface userDataAccess, HistoryOutputBoundary presenter) {
        this.userDataAccess = userDataAccess;
        this.presenter = presenter;
    }

    public void checkhistory() {
        User user = userDataAccess.getCurrentUser();
        HistoryOutputData outputData;
        outputData = new HistoryOutputData(user.getnumresume(),0,0, user);

        presenter.present(outputData);
    }
}
