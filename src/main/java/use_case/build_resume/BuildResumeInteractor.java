// use_case/build_resume/BuildResumeInteractor.java
package use_case.build_resume;

import data_access.UserDataAccessInterface;
import entity.CommonResume;
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

    public void setChatGPTService(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    @Override
    public void buildResume(BuildResumeInputData inputData) {
        String jobDescription = inputData.getJobDescription();
        int templateNumber = inputData.getTemplateNumber();

        // Check if the job description is null or empty
        if (jobDescription == null || jobDescription.trim().isEmpty()) {
            BuildResumeOutputData outputData = new BuildResumeOutputData("", "Job description is empty");
            presenter.present(outputData);
            return;
        }

        String resumeContent = chatGPTService.generateResume(
                inputData.getUserInfo(),
                jobDescription,
                templateNumber
        );

        CommonResume resume = new CommonResume();
        resume.setResumeName("Generated using Template " + templateNumber);
        resume.setResumeContent(resumeContent);

        userDataAccess.addResume(resume, inputData.getUsername());

        BuildResumeOutputData outputData = new BuildResumeOutputData(resumeContent, "Resume generated successfully");
        presenter.present(outputData);
    }
}