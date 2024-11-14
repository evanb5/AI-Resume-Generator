// use_case/build_cv/BuildCVInputData.java
package use_case.build_cv;

import entity.User;

public class BuildCVInputData {
    private User user;
    private String jobDescription;
    private String templateChoice;

    public BuildCVInputData(User user, String jobDescription, String templateChoice) {
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
