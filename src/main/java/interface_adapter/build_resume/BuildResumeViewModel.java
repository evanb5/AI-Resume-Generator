// interface_adapter/build_resume/BuildResumeViewModel.java
package interface_adapter.build_resume;

import interface_adapter.ViewModel;

public class BuildResumeViewModel extends ViewModel<BuildResumeState> {
    private String formattedResume;
    private String message;

    public BuildResumeViewModel() {
        setState(new BuildResumeState());
        this.formattedResume = "";
        this.message = "";
    }

    public String getFormattedResume() {
        return formattedResume;
    }

    public void setFormattedResume(String formattedResume) {
        this.formattedResume = formattedResume;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
