// interface_adapter/login/LoginPresenter.java
package interface_adapter.login;

import use_case.login.*;

public class LoginPresenter implements LoginOutputBoundary {
    private LoginViewModel viewModel;

    public LoginPresenter( LoginViewModel viewModel ) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(LoginOutputData outputData) {
        final LoginState loginState = viewModel.getState();
        this.viewModel.setState(loginState);
        viewModel.firePropertyChanged();


        viewModel.setSuccess(outputData.isSuccess());
        viewModel.setMessage(outputData.getMessage());
    }

    public LoginViewModel getViewModel() {
        return viewModel;
    }
}
