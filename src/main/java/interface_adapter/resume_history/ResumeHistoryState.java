package interface_adapter.resume_history;

public class ResumeHistoryState {
    private int resumes;
    private String resumeContent;
    private String message;

    public void setResumes(int resumes) { this.resumes = resumes; }

    public int getResumes() { return resumes; }

    public void setResumeContent(String resumeContent) { this.resumeContent = resumeContent; }

    public String getResumeContent() { return resumeContent; }

    public void setMessage(String message) { this.message = message; }

    public String getMessage() { return message; }
}
