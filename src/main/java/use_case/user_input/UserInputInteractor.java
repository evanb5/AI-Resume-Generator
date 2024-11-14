// use_case/user_input/UserInputInteractor.java
package use_case.user_input;

import data_access.UserDataAccessInterface;
import entity.User;

public class UserInputInteractor implements UserInputInputBoundary {
    private UserDataAccessInterface userDataAccess;
    private UserInputOutputBoundary presenter;

    public UserInputInteractor(UserDataAccessInterface userDataAccess, UserInputOutputBoundary presenter) {
        this.userDataAccess = userDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void updateUserData(UserInputData inputData) {
        User user = inputData.getUser();
        userDataAccess.updateUser(user);
        UserInputOutputData outputData = new UserInputOutputData(true, "User information updated successfully");
        presenter.present(outputData);
    }
}
