// use_case/give_suggestions/GiveSuggestionsInputData.java
package use_case.give_suggestions;

import entity.User;

public class GiveSuggestionsInputData {
    private String insertedResume;
    private String jobDescription;

    public GiveSuggestionsInputData(String insertedResume, String jobDescription) {
        this.jobDescription = jobDescription;
        this.insertedResume = insertedResume;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public String getInsertedResume() { return insertedResume; }
}
