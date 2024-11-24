package interface_adapter.login;

import entity.User;

public class LoginState {
    private User user;
    private String userName = "";
    private String password = "";

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
