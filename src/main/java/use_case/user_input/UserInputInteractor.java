// use_case/user_input/UserInputInteractor.java
package use_case.user_input;

import data_access.UserDataAccessInterface;
import entity.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserInputInteractor implements UserInputInputBoundary {
    private UserDataAccessInterface userDataAccess;
    private UserInputOutputBoundary presenter;

    public UserInputInteractor(UserDataAccessInterface userDataAccess, UserInputOutputBoundary presenter) {
        this.userDataAccess = userDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void updateUserData(UserInputData inputData) {
        User user = userDataAccess.getCurrentUser();
        user.setFullName(inputData.getFullname());
        user.setEmail(inputData.getEmail());
        user.setWorkExperience(Arrays.asList(inputData.getWorkexperience()));
        user.setEducation(Arrays.asList(inputData.getEducation()));
        user.setSkills(Arrays.asList(inputData.getSkills()));
        userDataAccess.updateUser(user);
        UserInputOutputData outputData = new UserInputOutputData(true);
        presenter.present(outputData);
    }

    @Override
    public void getUserData(){
        User user = userDataAccess.getCurrentUser();
        if(user != null){
            UserInputOutputDataforrefresh outputDataforrefresh = new UserInputOutputDataforrefresh(user.getFullName(),
                    user.getEmail(),user.getWorkExperience(),user.getEducation(),user.getSkills());
            presenter.refresh(outputDataforrefresh);
        }
    }
}
