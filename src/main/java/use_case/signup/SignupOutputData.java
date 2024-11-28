// use_case/signup/SignupOutputData.java
package use_case.signup;

public class SignupOutputData {
    private boolean success;
    private String message;
    private String username;

    public SignupOutputData(boolean success, String message, String username) {
        this.success = success;
        this.message = message;
        this.username = username;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() { return username; }

}
