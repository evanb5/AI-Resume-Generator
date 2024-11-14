// interface_adapter/build_cv/BuildCVPresenter.java
package interface_adapter.build_cv;

import use_case.build_cv.*;

public class BuildCVPresenter implements BuildCVOutputBoundary {
    private BuildCVViewModel viewModel;

    public BuildCVPresenter() {
        this.viewModel = new BuildCVViewModel();
    }

    @Override
    public void present(BuildCVOutputData outputData) {
        viewModel.setFormattedCV(outputData.getFormattedCV());
        viewModel.setMessage(outputData.getMessage());
    }

    public BuildCVViewModel getViewModel() {
        return viewModel;
    }
}
