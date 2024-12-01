package entity;

public class CommonResume implements Resume {
    private String resumeName;
    private String resumeContent;

    @Override
    public String getResumeName() {
        return resumeName;
    }

    @Override
    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    @Override
    public String getResumeContent() {
        System.out.println("Fetching resume content: " + resumeContent);
        return resumeContent;
    }

    @Override
    public void setResumeContent(String resumeContent) {
        this.resumeContent = resumeContent;
    }

    @Override
    public String toString() {
        // Provide a meaningful string representation
        return "Resume Name: " + resumeName + "\n" + "Content:\n" + resumeContent;
    }
}
