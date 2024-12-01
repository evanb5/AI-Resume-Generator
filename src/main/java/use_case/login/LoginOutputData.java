// use_case/login/LoginOutputData.java
package use_case.login;

import entity.User;

public class LoginOutputData {
    private boolean success;
    private String message;

    public LoginOutputData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

}
