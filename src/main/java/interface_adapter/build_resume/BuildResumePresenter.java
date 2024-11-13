// interface_adapter/build_resume/BuildResumePresenter.java
package interface_adapter.build_resume;

import use_case.build_resume.*;

public class BuildResumePresenter implements BuildResumeOutputBoundary {
    private BuildResumeViewModel viewModel;

    public BuildResumePresenter() {
        this.viewModel = new BuildResumeViewModel();
    }

    @Override
    public void present(BuildResumeOutputData outputData) {
        viewModel.setFormattedResume(outputData.getFormattedResume());
        viewModel.setMessage(outputData.getMessage());
    }

    public BuildResumeViewModel getViewModel() {
        return viewModel;
    }
}
