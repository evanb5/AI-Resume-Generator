package use_case.resume_history;

import java.util.List;

public class ResumeHistoryOutputData {
    private List<String> resumeTitles;
    private String resumeContent;
    private String message;

    public ResumeHistoryOutputData(List<String> resumeTitles, String resumeContent, String message) {
        this.resumeTitles = resumeTitles;
        this.resumeContent = resumeContent;
        this.message = message;
    }

    public List<String> getResumeTitles() {
        return resumeTitles;
    }

    public String getResumeContent() {
        return resumeContent;
    }

    public String getMessage() {
        return message;
    }
}
