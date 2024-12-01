// use_case/build_cv/BuildCVInputData.java
package use_case.build_cv;

import entity.User;

public class BuildCVInputData {
    private String jobDescription;
    private String cvTitle;

    public BuildCVInputData(String jobDescription, String cvTitle) {
        this.jobDescription = jobDescription;
        this.cvTitle = cvTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }


    public String getCvTitle() { return cvTitle; }
}
