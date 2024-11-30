package interface_adapter.resume_history;

import java.util.List;

public class ResumeHistoryViewModel {
    private int resumes;
    private String resumeContent;
    private String message;

    public int getResumes() {
        return resumes;
    }

    public void setResumes(int resumes) {
        this.resumes = resumes;
    }

    public String getResumeContent() {
        return resumeContent;
    }

    public void setResumeContent(String resumeContent) {
        this.resumeContent = resumeContent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
