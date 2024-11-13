// use_case/user_input/UserInputData.java
package use_case.user_input;

import entity.User;

public class UserInputData {
    private User user;

    public UserInputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
