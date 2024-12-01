// use_case/login/LoginInteractor.java
package use_case.login;

import data_access.UserDataAccessInterface;
import entity.User;

public class LoginInteractor implements LoginInputBoundary {
    private UserDataAccessInterface userDataAccess;
    private LoginOutputBoundary presenter;

    public LoginInteractor(UserDataAccessInterface userDataAccess, LoginOutputBoundary presenter) {
        this.userDataAccess = userDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void login(LoginInputData inputData) {
        User user = userDataAccess.getUser(inputData.getUsername());
        LoginOutputData outputData;

        if (user == null) {
            // Handle case where the user is not found
            outputData = new LoginOutputData(false, "User not found");
        } else if (user.getPassword().equals(inputData.getPassword())) {
            // Login successful
            outputData = new LoginOutputData(true, "Login successful");
            userDataAccess.setCurrentUser(user);
        } else {
            // Password incorrect
            outputData = new LoginOutputData(false, "Incorrect password");
        }

        presenter.present(outputData);
    }
}
