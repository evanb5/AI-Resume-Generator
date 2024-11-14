// interface_adapter/login/LoginController.java
package interface_adapter.login;

import use_case.login.*;

public class LoginController {
    private LoginInputBoundary interactor;

    public LoginController(LoginInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void login(String username, String password) {
        LoginInputData inputData = new LoginInputData(username, password);
        interactor.login(inputData);
    }
}
