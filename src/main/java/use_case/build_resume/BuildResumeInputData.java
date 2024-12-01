// use_case/build_resume/BuildResumeInputData.java
package use_case.build_resume;

public class BuildResumeInputData {
    private String username;
    private String userInfo;
    private String jobDescription;
    private int templateNumber;

    public BuildResumeInputData(String username, String userinfo, String jobDescription, int templateNumber) {
        this.username = username;
        this.userInfo = userinfo;
        this.jobDescription = jobDescription;
        this.templateNumber = templateNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public int getTemplateNumber() {
        return templateNumber;
    }
}
