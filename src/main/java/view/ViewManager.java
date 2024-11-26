// view/ViewManager.java
package view;

import javax.swing.*;
import java.awt.*;

import interface_adapter.CVhistory.CVhistoryController;
import interface_adapter.CVhistory.CVhistoryPresenter;
import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryPresenter;
import interface_adapter.history.HistoryViewModel;
import interface_adapter.login.*;
import interface_adapter.signup.*;
import interface_adapter.user_input.*;
import interface_adapter.build_resume.*;
import interface_adapter.build_cv.*;
import interface_adapter.give_suggestions.*;

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

    public ViewManager(
            LoginController loginController,
            SignupController signupController,
            UserInputController userInputController,
            BuildResumeController buildResumeController,
            BuildCVController buildCVController,
            GiveSuggestionsController giveSuggestionsController,

            HistoryController historyController,
            CVhistoryController CVhistorycontroller,
            LoginViewModel loginViewModel,
            SignupViewModel signupViewModel,
            UserInputPresenter userInputPresenter,
            BuildResumePresenter buildResumePresenter,
            BuildCVPresenter buildCVPresenter,
            GiveSuggestionsPresenter giveSuggestionsPresenter,
            HistoryPresenter historyPresenter,
            CVhistoryPresenter CVHistorypresenter

    ) {
        frame = new JFrame("AI Resume Generator");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        loginView = new LoginView(this, loginViewModel);
        loginView.setLoginController(loginController);
        signupView = new SignupView(this, signupViewModel);
        signupView.setSignupController(signupController);
        userInputView = new UserInputView(this, userInputController, userInputPresenter);
        buildResumeView = new BuildResumeView(this, buildResumeController, buildResumePresenter);
        buildCVView = new BuildCVView(this, buildCVController, buildCVPresenter);
        giveSuggestionsView = new GiveSuggestionsView(this, giveSuggestionsController, giveSuggestionsPresenter);
        historyView = new HistoryView(this, historyPresenter, historyController);
        cvHistoryView = new CVHistoryView(this, CVhistorycontroller, CVHistorypresenter);

        mainPanel.add(loginView, "LoginView");
        mainPanel.add(signupView, "SignupView");
        mainPanel.add(userInputView, "UserInputView");
        mainPanel.add(buildResumeView, "BuildResumeView");
        mainPanel.add(buildCVView, "BuildCVView");
        mainPanel.add(giveSuggestionsView, "GiveSuggestionsView");
        mainPanel.add(historyView, "HistoryView");
        mainPanel.add(cvHistoryView, "HistoryCVView");

        frame.add(mainPanel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showLoginView() {
        cardLayout.show(mainPanel, "LoginView");
        frame.setVisible(true);
    }

    public void showSignupView() {
        cardLayout.show(mainPanel, "SignupView");
    }

    public void showUserInputView() {
        cardLayout.show(mainPanel, "UserInputView");
    }

    public void showBuildResumeView() {
        cardLayout.show(mainPanel, "BuildResumeView");
    }

    public void showBuildCVView() {
        cardLayout.show(mainPanel, "BuildCVView");
    }

    public void showGiveSuggestionsView() {
        cardLayout.show(mainPanel, "GiveSuggestionsView");
    }


    public void showHistoryView() {
        historyView.refreshnow();
        cardLayout.show(mainPanel, "HistoryView");}

    public void showHistoryCVView() {
        cvHistoryView.refreshcv();
        cardLayout.show(mainPanel, "HistoryCVView");
    }

    public void showPastResumesView() {
        PastResumeView.
    }
}
