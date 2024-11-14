// use_case/signup/SignupInputData.java
package use_case.signup;

public class SignupInputData {
    private String username;
    private String password;
    private String email;

    public SignupInputData(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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
