package use_case.CVhistory;

import data_access.UserDataAccessInterface;
import entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CVhistoryInteractor implements CVhistoryInputBoundary {
    private UserDataAccessInterface userDataAccess;
    private CVhistoryOutputBoundary presenter;

    public CVhistoryInteractor(UserDataAccessInterface userDataAccess, CVhistoryOutputBoundary presenter) {
        this.userDataAccess = userDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void CVhistory(CVhistoryInputData inputData) {
        User user = userDataAccess.getCurrentUser();
        if (user == null) {
            // No user is logged in, return empty data
            CVhistoryOutputData outputData = new CVhistoryOutputData("", new ArrayList<>());
            this.presenter.present(outputData);
            return;
        }

        String username = user.getUsername();
        Map<String, String> CVhistory = userDataAccess.getCvs(username);
        int index = inputData.getIndex();

        List<String> keys = new ArrayList<>(CVhistory.keySet());
        if (index >= 0 && index < keys.size()) {
            String key = keys.get(index);
            CVhistoryOutputData outputData = new CVhistoryOutputData(userDataAccess.getCvContent(username, key), keys);
            this.presenter.present(outputData);
        } else if (index == -1) {
            CVhistoryOutputData outputData = new CVhistoryOutputData("", keys);
            this.presenter.present(outputData);
        } else {
            CVhistoryOutputData outputData = new CVhistoryOutputData("", new ArrayList<>());
            this.presenter.present(outputData);
        }
    }

}


