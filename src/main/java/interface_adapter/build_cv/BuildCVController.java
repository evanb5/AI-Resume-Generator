// interface_adapter/build_cv/BuildCVController.java
package interface_adapter.build_cv;

import use_case.build_cv.*;

public class BuildCVController {
    private BuildCVInputBoundary interactor;

    public BuildCVController(BuildCVInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void buildCV(BuildCVInputData inputData) {
        interactor.buildCV(inputData);
    }
}
