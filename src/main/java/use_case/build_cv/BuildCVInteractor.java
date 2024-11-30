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
        this.chatGPTService = new ChatGPTService();
    }

    /**
     * Builds a CV using the GPT api with the given job description, template choice,
     * and user information from the input data. It then returns the CV as a text and pdf file
     * and updates the user within the program's data access object.
     * @param inputData all the input data from the user required to create the resume. It contains the user
     *                  information, job description, template choice, and title for the CV.
     */
    @Override
    public void buildCV(BuildCVInputData inputData) {
        User user = userDataAccess.getCurrentUser();
        String jobDescription = inputData.getJobDescription();
        String templateChoice = inputData.getTemplateChoice();
        String cvTitle = inputData.getCvTitle();

        String userInfo = extractUserInfo(user);

        String cvContent = chatGPTService.generateCV(userInfo, jobDescription);
        String formattedCV = applyTemplate(cvContent, templateChoice);

        user.addCv(cvTitle, formattedCV);
        userDataAccess.updateUser(user);

        BuildCVOutputData outputData = new BuildCVOutputData(formattedCV, "CV generated successfully");
        presenter.present(outputData);
    }

    /**
     * Uses the information from the user class and converts it into a usable format for the GPT to process
     * @param user user class which contains the user's name, email address, working experience, educational background,
     * and skills
     * @return returns the user information in a usable String format
     */
    private String extractUserInfo(User user) {
        return "Name: " + user.getFullName() + "\n" +
                "Mail: " + user.getEmail() + "\n" +
                "working experience: " + String.join(", ", user.getWorkExperience()) + "\n" +
                "Educational background: " + String.join(", ", user.getEducation()) + "\n" +
                "Skills: " + String.join(", ", user.getSkills());
    }

    private String applyTemplate(String cvContent, String templateChoice) {
        return "template: " + templateChoice + "\ncontents of the CV\n" + cvContent;
    }
}
