// interface_adapter/signup/SignupPresenter.java
package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import use_case.signup.*;

public class SignupPresenter implements SignupOutputBoundary {
    private SignupViewModel viewModel;
    private ViewManagerModel viewManagerModel;

    public SignupPresenter(ViewManagerModel viewManagerModel, SignupViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void present(SignupOutputData outputData) {
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void failView(String errorMessage) {
        final SignupState signupState = viewModel.getState();
        signupState.setUsernameError(errorMessage);
        viewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoginView() {

    }

    public SignupViewModel getViewModel() {
        return viewModel;
    }
}
