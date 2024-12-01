// use_case/build_resume/BuildResumeInputData.java
package use_case.build_resume;

import entity.User;

public class BuildResumeInputData {
    private String jobDescription;
    private int templateNumber;

    public BuildResumeInputData(String jobDescription, int templateNumber) {
        this.jobDescription = jobDescription;
        this.templateNumber = templateNumber;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public int getTemplateNumber() {
        return templateNumber;
    }
}
