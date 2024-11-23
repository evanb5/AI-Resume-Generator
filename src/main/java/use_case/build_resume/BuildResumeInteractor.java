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
        int templateNumber = inputData.getTemplateNumber();

        String userInfo = extractUserInfo(user);

        String resumeContent = chatGPTService.generateResume(userInfo, jobDescription, templateNumber);

        BuildResumeOutputData outputData = new BuildResumeOutputData(resumeContent, "Resume generated successfully");
        presenter.present(outputData);
    }

    private String extractUserInfo(User user) {
        return "Name: " + user.getFullName() + "\n" +
                "Mail: " + user.getEmail() + "\n" +
                "Working Experience: " + String.join(", ", user.getWorkExperience()) + "\n" +
                "Educational background: " + String.join(", ", user.getEducation()) + "\n" +
                "Skills: " + String.join(", ", user.getSkills());
    }
}
