package interface_adapter.resume_history;

import java.util.List;

public class ResumeHistoryViewModel {
    private List<String> resumeTitles;
    private String resumeContent;
    private String message;

    public List<String> getResumeTitles() {
        return resumeTitles;
    }

    public void setResumeTitles(List<String> resumeTitles) {
        this.resumeTitles = resumeTitles;
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
