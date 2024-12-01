// interface_adapter/build_resume/BuildResumePresenter.java
package interface_adapter.build_resume;

import use_case.build_resume.*;

public class BuildResumePresenter implements BuildResumeOutputBoundary {
    private BuildResumeViewModel buildResumeViewModel;

    public BuildResumePresenter(BuildResumeViewModel viewModel) {
        buildResumeViewModel =  viewModel;
    }

    @Override
    public void present(BuildResumeOutputData outputData) {
        final BuildResumeState buildResumeState = buildResumeViewModel.getState();
        buildResumeState.setFormattedResume(outputData.getFormattedResume());
        buildResumeState.setMessage(outputData.getMessage());
        buildResumeViewModel.setState(buildResumeState);
        buildResumeViewModel.firePropertyChanged();

        buildResumeViewModel.setFormattedResume(outputData.getFormattedResume());
        buildResumeViewModel.setMessage(outputData.getMessage());
    }

    public BuildResumeViewModel getBuildResumeViewModel() {
        return buildResumeViewModel;
    }
}
