// use_case/signup/SignupOutputData.java
package use_case.signup;

public class SignupOutputData {
    private boolean success;
    private String message;

    public SignupOutputData(boolean success, String message) {
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
