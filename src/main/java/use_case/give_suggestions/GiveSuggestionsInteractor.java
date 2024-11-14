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
        this.chatGPTService = new ChatGPTService("YOUR_API_KEY", "YOUR_ENDPOINT");
    }

    @Override
    public void giveSuggestions(GiveSuggestionsInputData inputData) {
        User user = inputData.getUser();
        String jobDescription = inputData.getJobDescription();

        String userInfo = extractUserInfo(user);

        String suggestions = chatGPTService.generateSuggestions(userInfo, jobDescription);

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
