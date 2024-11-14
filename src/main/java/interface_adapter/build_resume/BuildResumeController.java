// interface_adapter/build_resume/BuildResumeController.java
package interface_adapter.build_resume;

import use_case.build_resume.*;

public class BuildResumeController {
    private BuildResumeInputBoundary interactor;

    public BuildResumeController(BuildResumeInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void buildResume(BuildResumeInputData inputData) {
        interactor.buildResume(inputData);
    }
}
