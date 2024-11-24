// use_case/give_suggestions/GiveSuggestionsInputData.java
package use_case.give_suggestions;

import entity.User;

public class GiveSuggestionsInputData {
    private User user;
    private String insertedResume;
    private String jobDescription;

    public GiveSuggestionsInputData(User user, String insertedResume, String jobDescription) {
        this.user = user;
        this.jobDescription = jobDescription;
        this.insertedResume = insertedResume;
    }

    public User getUser() {
        return user;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public String getInsertedResume() { return insertedResume; }
}
