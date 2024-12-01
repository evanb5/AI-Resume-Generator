// interface_adapter/user_input/UserInputPresenter.java
package interface_adapter.user_input;

import use_case.user_input.*;

public class UserInputPresenter implements UserInputOutputBoundary {
    private UserInputViewModel viewModel;

    public UserInputPresenter(UserInputViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(UserInputOutputData outputData) {
        final InputUserState state = viewModel.getState();
        state.setSuccess(outputData.isSuccess());
        viewModel.setState(state);
        viewModel.firePropertyChanged();

        viewModel.setSuccess(outputData.isSuccess());
    }

    @Override
    public void refresh(UserInputOutputDataforrefresh outputData) {
        final InputUserState state = viewModel.getState();
        state.setFullname(outputData.getFullname());
        state.setEmail(outputData.getEmail());
        state.setWorkexperience(outputData.getWorkexperience());
        state.setEducation(outputData.getEducation());
        state.setSkills(outputData.getSkills());
        viewModel.setState(state);
        viewModel.firePropertyChanged();

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
