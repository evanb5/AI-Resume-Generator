// use_case/signup/SignupInteractor.java
package use_case.signup;

import data_access.UserDataAccessInterface;
import entity.*;

public class SignupInteractor implements SignupInputBoundary {
    private UserDataAccessInterface userDataAccess;
    private UserFactory userFactory;
    private SignupOutputBoundary presenter;

    public SignupInteractor(UserDataAccessInterface userDataAccess, UserFactory userFactory, SignupOutputBoundary presenter) {
        this.userDataAccess = userDataAccess;
        this.userFactory = userFactory;
        this.presenter = presenter;
    }

    @Override
    public void signup(SignupInputData inputData) {
        User existingUser = userDataAccess.getUser(inputData.getUsername());
        SignupOutputData outputData;

        if (existingUser != null) {
            outputData = new SignupOutputData(false, "Username already exists", null);
        } else {
            User newUser = userFactory.createUser();
            newUser.setUsername(inputData.getUsername());
            newUser.setPassword(inputData.getPassword());
            newUser.setEmail(inputData.getEmail());
            userDataAccess.saveUser(newUser);
            outputData = new SignupOutputData(true, "Register Successfully", inputData.getUsername());
        }

        presenter.present(outputData);
    }
}
