// interface_adapter/signup/SignupController.java
package interface_adapter.signup;

import use_case.signup.*;

public class SignupController {
    private SignupInputBoundary interactor;

    public SignupController(SignupInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void signup(String username, String password, String email) {
        SignupInputData inputData = new SignupInputData(username, password, email);
        interactor.signup(inputData);
    }
}
