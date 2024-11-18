// use_case/signup/SignupOutputBoundary.java
package use_case.signup;

public interface SignupOutputBoundary {
    void present(SignupOutputData outputData);

    void failView(String errorMessage);

    void switchToLoginView();
}
