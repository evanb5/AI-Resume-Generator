// use_case/build_resume/BuildResumeInputData.java
package use_case.build_resume;

import entity.User;

public class BuildResumeInputData {
    private User user;
    private String jobDescription;
    private String templateChoice;

    public BuildResumeInputData(User user, String jobDescription, String templateChoice) {
        this.user = user;
        this.jobDescription = jobDescription;
        this.templateChoice = templateChoice;
    }

    public User getUser() {
        return user;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public String getTemplateChoice() {
        return templateChoice;
    }
}
