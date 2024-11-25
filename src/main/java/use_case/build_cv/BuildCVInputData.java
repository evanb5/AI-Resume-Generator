// use_case/build_cv/BuildCVInputData.java
package use_case.build_cv;

import entity.User;

public class BuildCVInputData {
    private User user;
    private String jobDescription;
    private String templateChoice;
    private String cvTitle;

    public BuildCVInputData(User user, String jobDescription, String templateChoice, String cvTitle) {
        this.user = user;
        this.jobDescription = jobDescription;
        this.templateChoice = templateChoice;
        this.cvTitle = cvTitle;
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

    public String getCvTitle() { return templateChoice; }
}
