package use_case.CVhistory;

import data_access.UserDataAccessInterface;
import entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CVhistoryInteractor implements CVhistoryInputBoundary{
    private UserDataAccessInterface userDataAccess;
    private CVhistoryOutputBoundary presenter;

    public CVhistoryInteractor(UserDataAccessInterface userDataAccess, CVhistoryOutputBoundary presenter) {
        this.userDataAccess = userDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void CVhistory(CVhistoryInputData inputData) {
        User user = userDataAccess.getCurrentUser();
        int index = inputData.getIndex();
        if (user != null) {
            List<String> keys = new ArrayList<>(user.getCvs().keySet());
            if (index >= 0 && index < keys.size()) {
                CVhistoryOutputData outputData = new CVhistoryOutputData(user.getCvs().get(keys.get(index)), keys);
                user.setCVindex(index);
                this.presenter.present(outputData);
            } else if (index == -4) {
                CVhistoryOutputData outputData = new CVhistoryOutputData("", keys);
                this.presenter.present(outputData);
                user.setCVindex(-4);
            } else if (index == -2 && user.getCVindex() > 0) {
                int newIndex = user.getCVindex() - 1;
                CVhistoryOutputData outputData = new CVhistoryOutputData(user.getCvs().get(keys.get(newIndex)), keys);
                user.setCVindex(newIndex);
                this.presenter.present(outputData);
            } else if (index == -3 && user.getCVindex() >= 0 && user.getCVindex() < keys.size() - 1) {
                int newIndex = user.getCVindex() + 1;
                CVhistoryOutputData outputData = new CVhistoryOutputData(user.getCvs().get(keys.get(newIndex)), keys);
                user.setCVindex(newIndex);
                this.presenter.present(outputData);
            } else {
                if (user.getCVindex() != -4 && user.getCVindex() >= 0 && user.getCVindex() < keys.size()) {
                    CVhistoryOutputData outputData = new CVhistoryOutputData(user.getCvs().get(keys.get(user.getCVindex())), keys);
                    this.presenter.present(outputData);
                } else {
                    CVhistoryOutputData outputData = new CVhistoryOutputData("", keys);
                    this.presenter.present(outputData);
                }
            }
        } else {
            CVhistoryOutputData outputData = new CVhistoryOutputData("", new ArrayList<>());
            this.presenter.present(outputData);
        }
    }

}
