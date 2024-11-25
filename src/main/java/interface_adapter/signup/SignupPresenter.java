// interface_adapter/signup/SignupPresenter.java
package interface_adapter.signup;

import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.signup.*;

public class SignupPresenter implements SignupOutputBoundary {
    private final SignupViewModel viewModel;
    private final LoginViewModel loginViewModel;

    public SignupPresenter(LoginViewModel loginViewModel, SignupViewModel viewModel) {
        this.loginViewModel = loginViewModel;
        this.viewModel = viewModel;
    }

    @Override
    public void present(SignupOutputData outputData) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setusername(outputData.getUsername());
        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewModel.setSuccess(outputData.isSuccess());
        viewModel.setMessage(outputData.getMessage());
    }

    public SignupViewModel getViewModel() {
        return viewModel;
    }
}
