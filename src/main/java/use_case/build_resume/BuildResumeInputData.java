// use_case/build_resume/BuildResumeInputData.java
package use_case.build_resume;

import entity.User;

public class BuildResumeInputData {
    private User user;
    private String jobDescription;
    private int templateNumber;

    public BuildResumeInputData(User user, String jobDescription, int templateNumber) {
        this.user = user;
        this.jobDescription = jobDescription;
        this.templateNumber = templateNumber;
    }

    public User getUser() {
        return user;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public int getTemplateNumber() {
        return templateNumber;
    }
}
