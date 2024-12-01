// interface_adapter/build_cv/BuildCVPresenter.java
package interface_adapter.build_cv;

import use_case.build_cv.*;

public class BuildCVPresenter implements BuildCVOutputBoundary {
    private BuildCVViewModel viewModel;

    public BuildCVPresenter(BuildCVViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(BuildCVOutputData outputData) {
        final BuildCVState buildCVState = viewModel.getState();
        buildCVState.setFormattedCV(outputData.getFormattedCV());
        buildCVState.setMessage(outputData.getMessage());
        viewModel.setState(buildCVState);
        viewModel.firePropertyChanged();

        viewModel.setFormattedCV(outputData.getFormattedCV());
        viewModel.setMessage(outputData.getMessage());
    }

    public BuildCVViewModel getViewModel() {
        return viewModel;
    }
}
