// interface_adapter/signup/SignupViewModel.java
package interface_adapter.signup;

import interface_adapter.ViewModel;

public class SignupViewModel extends ViewModel<SignupState> {
    private boolean success;
    private String message;

public SignupViewModel() {
    setState( new SignupState());
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
}
