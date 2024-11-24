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

    public void present(HistoryOutputBoundary outputData) {
    }
}
