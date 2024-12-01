// use_case/give_suggestions/GiveSuggestionsInteractor.java
package use_case.give_suggestions;

import data_access.UserDataAccessInterface;
import entity.User;
import services.ChatGPTService;

public class GiveSuggestionsInteractor implements GiveSuggestionsInputBoundary {
    private UserDataAccessInterface userDataAccess;
    private GiveSuggestionsOutputBoundary presenter;
    private ChatGPTService chatGPTService;

    public GiveSuggestionsInteractor(UserDataAccessInterface userDataAccess, GiveSuggestionsOutputBoundary presenter) {
        this.userDataAccess = userDataAccess;
        this.presenter = presenter;
        this.chatGPTService = new ChatGPTService();
    }

    public void setChatGPTService(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    /**
     * This program takes an inputted resume and offers suggestions based off the user's professional information and
     * the given description of the job they want to get using this resume using an GPT api. It returns the
     * information in both text and pdf format.
     * @param inputData all the personal and professional information of the user, the inputted resume, and the
     *                  job description is.
     */
    @Override
    public void giveSuggestions(GiveSuggestionsInputData inputData) {
        User user = userDataAccess.getCurrentUser();
        String jobDescription = inputData.getJobDescription();
        String insertedResume = inputData.getInsertedResume();
        String userInfo = extractUserInfo(user);

        String suggestions = chatGPTService.generateSuggestions(userInfo,insertedResume, jobDescription);

        GiveSuggestionsOutputData outputData = new GiveSuggestionsOutputData(suggestions, "Suggestions generated successfully");

        presenter.present(outputData);
    }

    private String extractUserInfo(User user) {
        return "Name: " + user.getFullName() + "\n" +
                "Mail: " + user.getEmail() + "\n" +
                "working experience: " + String.join(", ", user.getWorkExperience()) + "\n" +
                "Educational background: " + String.join(", ", user.getEducation()) + "\n" +
                "Skills: " + String.join(", ", user.getSkills());
    }
}
