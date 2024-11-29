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
    }

    @Override
    public void refresh(UserInputOutputDataforrefresh outputData) {
        viewModel.setFullname(outputData.getFullname());
        viewModel.setEmail(outputData.getEmail());
        viewModel.setWorkexperience(outputData.getWorkexperience());
        viewModel.setEducation(outputData.getEducation());
        viewModel.setSkills(outputData.getSkills());
    }

    public UserInputViewModel getViewModel() {
        return viewModel;
    }
}
