// interface_adapter/build_cv/BuildCVViewModel.java
package interface_adapter.build_cv;

import interface_adapter.ViewModel;

public class BuildCVViewModel extends ViewModel<BuildCVState> {
    private String formattedCV;
    private String message;

    public BuildCVViewModel() {
        setState(new BuildCVState());
        formattedCV = "";
        message = "";
    }

    public String getFormattedCV() {
        return formattedCV;
    }

    public void setFormattedCV(String formattedCV) {
        this.formattedCV = formattedCV;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
