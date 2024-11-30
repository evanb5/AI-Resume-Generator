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
        User user = userDataAccess.getCurrentUser();
        String jobDescription = inputData.getJobDescription();
        int templateNumber = inputData.getTemplateNumber();

        // Check if the job description is null or empty
        if (jobDescription == null || jobDescription.trim().isEmpty()) {
            BuildResumeOutputData outputData = new BuildResumeOutputData("", "Job description is empty");
            presenter.present(outputData);
            return;
        }

        // Proceed with generating the resume if the job description is valid
        String userInfo = extractUserInfo(user);
        String resumeContent = chatGPTService.generateResume(userInfo, jobDescription, templateNumber);

        BuildResumeOutputData outputData = new BuildResumeOutputData(resumeContent, "Resume generated successfully");

        user.addResume(resumeContent);
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
