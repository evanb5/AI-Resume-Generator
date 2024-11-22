// use_case/build_resume/BuildResumeInteractor.java
package use_case.build_resume;

import data_access.UserDataAccessInterface;
import entity.User;
import services.ChatGPTService;

public class BuildResumeInteractor implements BuildResumeInputBoundary {
    private UserDataAccessInterface userDataAccess;
    private BuildResumeOutputBoundary presenter;
    private ChatGPTService chatGPTService;

    public BuildResumeInteractor(UserDataAccessInterface userDataAccess, BuildResumeOutputBoundary presenter) {
        this.userDataAccess = userDataAccess;
        this.presenter = presenter;
        this.chatGPTService = new ChatGPTService();
    }

    @Override
    public void buildResume(BuildResumeInputData inputData) {

        User user = inputData.getUser();
        String jobDescription = inputData.getJobDescription();
        String templateChoice = inputData.getTemplateChoice();

        String userInfo = extractUserInfo(user);

        String resumeContent = chatGPTService.generateResume(userInfo, jobDescription);

        String formattedResume = applyTemplate(resumeContent, templateChoice);

        BuildResumeOutputData outputData = new BuildResumeOutputData(formattedResume, "Resume generated successfully");

        user.addResume(formattedResume);

        presenter.present(outputData);
    }

    private String extractUserInfo(User user) {
        return "Name: " + user.getFullName() + "\n" +
                "Mail: " + user.getEmail() + "\n" +
                "working experience: " + String.join(", ", user.getWorkExperience()) + "\n" +
                "Educational background: " + String.join(", ", user.getEducation()) + "\n" +
                "Skills: " + String.join(", ", user.getSkills());
    }

    private String applyTemplate(String resumeContent, String templateChoice) {
        return "template: " + templateChoice + "\n\n" + resumeContent;

    }
}
