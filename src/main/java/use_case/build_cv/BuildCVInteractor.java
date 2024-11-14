// use_case/build_cv/BuildCVInteractor.java
package use_case.build_cv;

import data_access.UserDataAccessInterface;
import entity.User;
import services.ChatGPTService;

public class BuildCVInteractor implements BuildCVInputBoundary {
    private UserDataAccessInterface userDataAccess;
    private BuildCVOutputBoundary presenter;
    private ChatGPTService chatGPTService;

    public BuildCVInteractor(UserDataAccessInterface userDataAccess, BuildCVOutputBoundary presenter) {
        this.userDataAccess = userDataAccess;
        this.presenter = presenter;
        this.chatGPTService = new ChatGPTService("YOUR_API_KEY", "YOUR_ENDPOINT");
    }

    @Override
    public void buildCV(BuildCVInputData inputData) {
        User user = inputData.getUser();
        String jobDescription = inputData.getJobDescription();
        String templateChoice = inputData.getTemplateChoice();

        String userInfo = extractUserInfo(user);

        String cvContent = chatGPTService.generateCV(userInfo, jobDescription);

        String formattedCV = applyTemplate(cvContent, templateChoice);

        BuildCVOutputData outputData = new BuildCVOutputData(formattedCV, "CV generated successfully");
        presenter.present(outputData);
    }

    private String extractUserInfo(User user) {
        return "Name: " + user.getFullName() + "\n" +
                "Mail: " + user.getEmail() + "\n" +
                "working experience: " + String.join(", ", user.getWorkExperience()) + "\n" +
                "Educational background: " + String.join(", ", user.getEducation()) + "\n" +
                "Skills: " + String.join(", ", user.getSkills());
    }

    private String applyTemplate(String cvContent, String templateChoice) {
        return "template: " + templateChoice + "\n\n" + cvContent;
    }
}
