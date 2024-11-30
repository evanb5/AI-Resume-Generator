package use_case.resume_history;

import java.util.List;

public class ResumeHistoryOutputData {
    private int resumes;
    private String resumeContent;
    private String message;

    public ResumeHistoryOutputData(int resumes, String resumeContent, String message) {
        this.resumes = resumes;
        this.resumeContent = resumeContent;
        this.message = message;
    }

    public int getResumes() {return resumes;}

    public String getResumeContent() {
        return resumeContent;
    }

    public String getMessage() {
        return message;
    }
}
