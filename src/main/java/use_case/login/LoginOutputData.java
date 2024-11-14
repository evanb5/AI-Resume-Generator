// use_case/login/LoginOutputData.java
package use_case.login;

import entity.User;

public class LoginOutputData {
    private boolean success;
    private String message;
    private User user;

    public LoginOutputData(boolean success, String message, User user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
