package interface_adapter.build_cv;

public class BuildCVState {
    String formattedCV;
    String message;

    public String getFormattedCV() {
        return formattedCV;
    }

    public void setFormattedCV(final String formattedCV) {
        this.formattedCV = formattedCV;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
