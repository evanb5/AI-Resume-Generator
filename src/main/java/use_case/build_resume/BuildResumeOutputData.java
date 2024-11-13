// use_case/build_resume/BuildResumeOutputData.java
package use_case.build_resume;

public class BuildResumeOutputData {
    private String formattedResume;
    private String message;

    public BuildResumeOutputData(String formattedResume, String message) {
        this.formattedResume = formattedResume;
        this.message = message;
    }

    public String getFormattedResume() {
        return formattedResume;
    }

    public String getMessage() {
        return message;
    }
}
