// use_case/build_cv/BuildCVInteractor.java
package use_case.build_cv;

import data_access.UserDataAccessInterface;
import entity.CV;
import entity.CommonCV;
import services.ChatGPTService;
import use_case.build_resume.BuildResumeOutputData;

public class BuildCVInteractor implements BuildCVInputBoundary {
    private UserDataAccessInterface userDataAccess;
    private BuildCVOutputBoundary presenter;
    private ChatGPTService chatGPTService;

    public BuildCVInteractor(UserDataAccessInterface userDataAccess, BuildCVOutputBoundary presenter) {
        this.userDataAccess = userDataAccess;
        this.presenter = presenter;
        this.chatGPTService = new ChatGPTService();
    }

    public void setChatGPTService(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    /**
     * Builds a CV using the GPT api with the given job description, template choice,
     * and user information from the input data. It then returns the CV as a text and pdf file
     * and updates the user within the program's data access object.
     *
     * @param inputData all the input data from the user required to create the resume. It contains the user
     *                  information, job description, template choice, and title for the CV.
     */
    @Override
    public void buildCV(BuildCVInputData inputData) {
        String username = userDataAccess.getCurrentUserName();
        String jobDescription = inputData.getJobDescription();
        String cvTitle = inputData.getCvTitle();

        // Check if the job description is null or empty
        if (jobDescription == null || jobDescription.trim().isEmpty()) {
            BuildCVOutputData outputData = new BuildCVOutputData("", "Job description is empty");
            presenter.present(outputData);
            return;
        }

        String cvContent = chatGPTService.generateCV(cvTitle, jobDescription);

        CV newCV = new CommonCV();
        newCV.setName(cvTitle);
        newCV.setCv(cvContent);

        userDataAccess.addCv(username, newCV);

        BuildCVOutputData outputData = new BuildCVOutputData(cvContent, "CV generated successfully");
        presenter.present(outputData);
    }
}
