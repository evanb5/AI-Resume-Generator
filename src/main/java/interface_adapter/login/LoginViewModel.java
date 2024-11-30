// interface_adapter/login/LoginViewModel.java
package interface_adapter.login;

import entity.User;
import interface_adapter.ViewModel;

public class LoginViewModel extends ViewModel<LoginState> {
    private boolean success;
    private String message;
    private User user;

    public LoginViewModel() {
        setState(new LoginState());
        success = false;
        message = "";
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
