// view/ViewManager.java
package view;

import javax.swing.*;
import java.awt.*;
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

    public ViewManager(
            LoginController loginController,
            SignupController signupController,
            UserInputController userInputController,
            BuildResumeController buildResumeController,
            BuildCVController buildCVController,
            GiveSuggestionsController giveSuggestionsController,
            LoginPresenter loginPresenter,
            SignupPresenter signupPresenter,
            UserInputPresenter userInputPresenter,
            BuildResumePresenter buildResumePresenter,
            BuildCVPresenter buildCVPresenter,
            GiveSuggestionsPresenter giveSuggestionsPresenter
    ) {
        frame = new JFrame("AI Resume Generator");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        loginView = new LoginView(this, loginController, loginPresenter);
        signupView = new SignupView(this, signupController, signupPresenter);
        userInputView = new UserInputView(this, userInputController, userInputPresenter);
        buildResumeView = new BuildResumeView(this, buildResumeController, buildResumePresenter);
        buildCVView = new BuildCVView(this, buildCVController, buildCVPresenter);
        giveSuggestionsView = new GiveSuggestionsView(this, giveSuggestionsController, giveSuggestionsPresenter);

        mainPanel.add(loginView, "LoginView");
        mainPanel.add(signupView, "SignupView");
        mainPanel.add(userInputView, "UserInputView");
        mainPanel.add(buildResumeView, "BuildResumeView");
        mainPanel.add(buildCVView, "BuildCVView");
        mainPanel.add(giveSuggestionsView, "GiveSuggestionsView");

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
}
