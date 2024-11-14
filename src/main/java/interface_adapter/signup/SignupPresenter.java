// interface_adapter/signup/SignupPresenter.java
package interface_adapter.signup;

import use_case.signup.*;

public class SignupPresenter implements SignupOutputBoundary {
    private SignupViewModel viewModel;

    public SignupPresenter() {
        this.viewModel = new SignupViewModel();
    }

    @Override
    public void present(SignupOutputData outputData) {
        viewModel.setSuccess(outputData.isSuccess());
        viewModel.setMessage(outputData.getMessage());
    }

    public SignupViewModel getViewModel() {
        return viewModel;
    }
}
