// use_case/build_resume/BuildResumeInteractor.java
package use_case.build_resume;

import data_access.UserDataAccessInterface;
import entity.*;
import services.ChatGPTService;
import session.UserSession;

public class BuildResumeInteractor implements BuildResumeInputBoundary {
    private UserDataAccessInterface userDataAccess;
    private BuildResumeOutputBoundary presenter;
    private ChatGPTService chatGPTService;
    private final ResumeFactory resumeFactory;

    public BuildResumeInteractor(UserDataAccessInterface userDataAccess, BuildResumeOutputBoundary presenter,
                                 ResumeFactory resumeFactory) {
        this.userDataAccess = userDataAccess;
        this.presenter = presenter;
        this.chatGPTService = new ChatGPTService();
        this.resumeFactory = resumeFactory;

    }

    public void setChatGPTService(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    @Override
    public void buildResume(BuildResumeInputData inputData) {
        User user = userDataAccess.getCurrentUser();
        String userInformation = generateUserInfo(user);
        String username = userDataAccess.getCurrentUserName();
        String jobDescription = inputData.getJobDescription();
        int templateNumber = inputData.getTemplateNumber();

        // Check if the job description is null or empty
        if (jobDescription == null || jobDescription.trim().isEmpty()) {
            BuildResumeOutputData outputData = new BuildResumeOutputData("", "Job description is empty");
            presenter.present(outputData);
            return;
        }

        String resumeContent = chatGPTService.generateResume(
                userInformation,
                jobDescription,
                templateNumber
        );

        Resume resume = resumeFactory.createResume();
        resume.setResumeName("Generated using Template " + templateNumber);
        resume.setResumeContent(resumeContent);

        userDataAccess.addResume(resume, username);

        BuildResumeOutputData outputData = new BuildResumeOutputData(resumeContent, "Resume generated successfully");
        presenter.present(outputData);
    }

    // Helper method to generate user info
    private String generateUserInfo(User user) {
        return "Name: " + user.getFullName() + "\n" +
                "Email: " + user.getEmail() + "\n" +
                "Work Experience: " + String.join(", ", user.getWorkExperience()) + "\n" +
                "Education: " + String.join(", ", user.getEducation()) + "\n" +
                "Skills: " + String.join(", ", user.getSkills());
    }
}