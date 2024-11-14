// interface_adapter/user_input/UserInputPresenter.java
package interface_adapter.user_input;

import use_case.user_input.*;

public class UserInputPresenter implements UserInputOutputBoundary {
    private UserInputViewModel viewModel;

    public UserInputPresenter() {
        this.viewModel = new UserInputViewModel();
    }

    @Override
    public void present(UserInputOutputData outputData) {
        viewModel.setSuccess(outputData.isSuccess());
        viewModel.setMessage(outputData.getMessage());
    }

    public UserInputViewModel getViewModel() {
        return viewModel;
    }
}
