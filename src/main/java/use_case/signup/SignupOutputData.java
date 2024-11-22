// use_case/signup/SignupOutputData.java
package use_case.signup;

public class SignupOutputData {
    private boolean success;
    private String message;
    private String username;
    private String password;
    private String email;

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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}
