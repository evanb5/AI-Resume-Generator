// use_case/user_input/UserInputOutputData.java
package use_case.user_input;

public class UserInputOutputData {
    private boolean success;
    private String message;

    public UserInputOutputData(boolean success, String message) {
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
