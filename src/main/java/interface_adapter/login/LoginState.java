package interface_adapter.login;

public class LoginState {
    private String userName = "";
    private String password = "";
    private String message = "";

    public String getUserName() {
        return userName;
    }

    public void setusername(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

}
