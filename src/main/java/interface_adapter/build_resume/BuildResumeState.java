package interface_adapter.build_resume;

public class BuildResumeState {
    private String formattedResume;
    private String message;

    public String getFormattedResume() { return formattedResume; }

    public void setFormattedResume(final String formattedResume) { this.formattedResume = formattedResume; }

    public String getMessage() { return message; }

    public void setMessage(final String message) { this.message = message; }
}
