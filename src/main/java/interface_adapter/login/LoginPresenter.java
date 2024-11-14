// interface_adapter/login/LoginPresenter.java
package interface_adapter.login;

import use_case.login.*;

public class LoginPresenter implements LoginOutputBoundary {
    private LoginViewModel viewModel;

    public LoginPresenter() {
        this.viewModel = new LoginViewModel();
    }

    @Override
    public void present(LoginOutputData outputData) {
        viewModel.setSuccess(outputData.isSuccess());
        viewModel.setMessage(outputData.getMessage());
        viewModel.setUser(outputData.getUser());
    }

    public LoginViewModel getViewModel() {
        return viewModel;
    }
}
