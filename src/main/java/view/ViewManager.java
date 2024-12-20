package view;

import javax.swing.*;
import java.awt.*;

import interface_adapter.CVhistory.CVhistoryController;
import interface_adapter.CVhistory.CVhistoryViewModel;
import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryViewModel;
import interface_adapter.login.*;
import interface_adapter.resume_history.ResumeHistoryViewModel;
import interface_adapter.signup.*;
import interface_adapter.user_input.*;
import interface_adapter.build_resume.*;
import interface_adapter.build_cv.*;
import interface_adapter.give_suggestions.*;
import interface_adapter.resume_history.ResumeHistoryController;

public class ViewManager {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private LoginView loginView;
    private SignupView signupView;
    private UserInputView userInputView;
    private BuildResumeView buildResumeView;
    private BuildCVView buildCVView;
    private GiveSuggestionsView giveSuggestionsView;
    private HistoryView historyView;
    private CVHistoryView cvHistoryView;
    private PastResumesView pastResumesView; // New view for Resume History

    public ViewManager(
            LoginController loginController,
            SignupController signupController,
            UserInputController userInputController,
            BuildResumeController buildResumeController,
            BuildCVController buildCVController,
            GiveSuggestionsController giveSuggestionsController,
            HistoryController historyController,
            CVhistoryController CVhistorycontroller,
            ResumeHistoryController resumeHistoryController, // New controller for Resume History
            LoginViewModel loginViewModel,
            SignupViewModel signupViewModel,
            UserInputViewModel userInputViewModel,
            BuildResumeViewModel buildResumeViewModel,
            BuildCVViewModel buildCVViewModel,
            GiveSuggestionsViewModel giveSuggestionsViewModel,
            HistoryViewModel historyViewModel,
            CVhistoryViewModel cVhistoryViewModel,
            ResumeHistoryViewModel resumeHistoryViewModel // New view model for Resume History
    ) {
        frame = new JFrame("AI Resume Generator");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        loginView = new LoginView(this, loginViewModel);
        loginView.setLoginController(loginController);
        signupView = new SignupView(this, signupViewModel);
        signupView.setSignupController(signupController);
        userInputView = new UserInputView(this, userInputController, userInputViewModel);
        buildResumeView = new BuildResumeView(this, buildResumeController, buildResumeViewModel);
        buildCVView = new BuildCVView(this, buildCVController, buildCVViewModel);
        giveSuggestionsView = new GiveSuggestionsView(this, giveSuggestionsController, giveSuggestionsViewModel);
        historyView = new HistoryView(this, historyViewModel, historyController);
        cvHistoryView = new CVHistoryView(this, CVhistorycontroller, cVhistoryViewModel);
        pastResumesView = new PastResumesView(this, resumeHistoryController, resumeHistoryViewModel); // Initialize PastResumesView

        mainPanel.add(loginView, "LoginView");
        mainPanel.add(signupView, "SignupView");
        mainPanel.add(userInputView, "UserInputView");
        mainPanel.add(buildResumeView, "BuildResumeView");
        mainPanel.add(buildCVView, "BuildCoverLetterView");
        mainPanel.add(giveSuggestionsView, "GiveSuggestionsView");
        mainPanel.add(historyView, "HistoryView");
        mainPanel.add(cvHistoryView, "HistoryCoverLetterView");
        mainPanel.add(pastResumesView, "PastResumesView"); // Add PastResumesView to mainPanel

        frame.add(mainPanel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showLoginView() {
        loginView.clearprevioususerinformation();
        cardLayout.show(mainPanel, "LoginView");
        frame.setVisible(true);
    }

    public void showSignupView() {
        signupView.clearall();
        cardLayout.show(mainPanel, "SignupView");
    }

    public void showUserInputView() {
        userInputView.refreshUserData();
        cardLayout.show(mainPanel, "UserInputView");
    }

    public void showBuildResumeView() {
        buildResumeView.clearView();
        cardLayout.show(mainPanel, "BuildResumeView");
    }

    public void showBuildCVView() {
        buildCVView.clearView();
        cardLayout.show(mainPanel, "BuildCoverLetterView");
    }

    public void showGiveSuggestionsView() {
        giveSuggestionsView.clearView();
        cardLayout.show(mainPanel, "GiveSuggestionsView");
    }

    public void showHistoryView() {
        historyView.refreshnow();
        cardLayout.show(mainPanel, "HistoryView");
    }

    public void showHistoryCVView() {
        cvHistoryView.refreshcv();
        cardLayout.show(mainPanel, "HistoryCoverLetterView");
    }

    public void showPastResumesView() { // New method to show PastResumesView
        pastResumesView.refreshResumes();
        cardLayout.show(mainPanel, "PastResumesView");
    }
}
