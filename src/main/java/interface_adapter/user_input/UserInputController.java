// interface_adapter/user_input/UserInputController.java
package interface_adapter.user_input;

import use_case.user_input.*;

public class UserInputController {
    private UserInputInputBoundary interactor;

    public UserInputController(UserInputInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void updateUserData(UserInputData inputData) {
        interactor.updateUserData(inputData);
    }
}
