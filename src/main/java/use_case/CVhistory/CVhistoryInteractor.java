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
        if(user != null) {
            if (index >= 0 && index  <= user.getCvs().size()) {
                List<String> keys = new ArrayList<>(user.getCvs().keySet());
                CVhistoryOutputData outputData = new CVhistoryOutputData(user.getCvs().get(keys.get(index)), keys);
                user.setCVindex(index);
                this.presenter.present(outputData);
            }else if(index == -4) {
                List<String> keys = new ArrayList<>(user.getCvs().keySet());
                CVhistoryOutputData outputData = new CVhistoryOutputData("", keys);
                this.presenter.present(outputData);
                user.setCVindex(-4);
            }else if(index == -2 && user.getCVindex()>0) {
                List<String> keys = new ArrayList<>(user.getCvs().keySet());
                CVhistoryOutputData outputData = new CVhistoryOutputData(user.getCvs().get(keys.get(user.getCVindex() - 1)), keys);
                user.setCVindex(user.getCVindex() - 1);
                this.presenter.present(outputData);
            }else if(index == -3 && user.getCVindex()<user.getCvs().size() && user.getCVindex()>=0) {
                List<String> keys = new ArrayList<>(user.getCvs().keySet());
                CVhistoryOutputData outputData = new CVhistoryOutputData(user.getCvs().get(keys.get(user.getCVindex() + 1)), keys);
                user.setCVindex(user.getCVindex() + 1);
                this.presenter.present(outputData);
            }else{
                if (user.getCVindex() != -4){
                    List<String> keys = new ArrayList<>(user.getCvs().keySet());
                    CVhistoryOutputData outputData = new CVhistoryOutputData(user.getCvs().get(keys.get(user.getCVindex())), keys);
                    this.presenter.present(outputData);
                }else {
                    List<String> keys = new ArrayList<>(user.getCvs().keySet());
                    CVhistoryOutputData outputData = new CVhistoryOutputData("", keys);
                    this.presenter.present(outputData);
                }
            }
        }else {
            CVhistoryOutputData outputData = new CVhistoryOutputData("",new ArrayList<>());
            this.presenter.present(outputData);
        }

}}
