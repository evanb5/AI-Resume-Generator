// view/UserInputView.java
package view;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;
import interface_adapter.user_input.*;
import session.UserSession;
import use_case.user_input.UserInputData;

public class UserInputView extends JPanel {
    private ViewManager viewManager;
    private UserInputController controller;
    private UserInputPresenter presenter;

    private JTextField fullNameField;
    private JTextField emailField;
    private JTextArea workExperienceArea;
    private JTextArea educationArea;
    private JTextArea skillsArea;
    private JButton saveButton;
    private JButton buildResumeButton;
    private JButton buildCVButton;
    private JButton giveSuggestionsButton;
    private JButton logoutButton;
    private JButton historyButton;
    private JLabel messageLabel;

    public UserInputView(ViewManager viewManager, UserInputController controller, UserInputPresenter presenter) {
        this.viewManager = viewManager;
        this.controller = controller;
        this.presenter = presenter;

        // Initialize components
        fullNameField = new JTextField(20);
        emailField = new JTextField(20);
        workExperienceArea = new JTextArea(5, 20);
        educationArea = new JTextArea(5, 20);
        skillsArea = new JTextArea(5, 20);
        saveButton = new JButton("Save");
        buildResumeButton = new JButton("Build Resume");
        buildCVButton = new JButton("Build CV");
        giveSuggestionsButton = new JButton("Give Suggestions");
        logoutButton = new JButton("Logout");
        historyButton = new JButton("History");
        messageLabel = new JLabel();

        // Layout components
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Full Name:"));
        add(fullNameField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Work Experience (one per line):"));
        add(new JScrollPane(workExperienceArea));
        add(new JLabel("Education (one per line):"));
        add(new JScrollPane(educationArea));
        add(new JLabel("Skills (one per line):"));
        add(new JScrollPane(skillsArea));
        add(saveButton);
        add(buildResumeButton);
        add(buildCVButton);
        add(giveSuggestionsButton);
        add(historyButton);
        add(logoutButton);
        add(messageLabel);

        // Add action listeners
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserInputData inputData = new UserInputData(fullNameField.getText(),emailField.getText(),
                        workExperienceArea.getText().split("\n"), educationArea.getText().split("\n"),
                        skillsArea.getText().split("\n"));
                controller.updateUserData(inputData);
                // Update view
                UserInputViewModel viewModel = presenter.getViewModel();
                if (viewModel.isSuccess()){
                    messageLabel.setText("User information updated successfully");
                }else{
                    messageLabel.setText("User information update failed");
                }
            }
        });

        buildResumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManager.showBuildResumeView();
            }
        });

        buildCVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManager.showBuildCVView();
            }
        });

        giveSuggestionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManager.showGiveSuggestionsView();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserSession.getInstance().setCurrentUser(null);
                viewManager.showLoginView();
            }
        });

        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManager.showHistoryView();
            }
        });
    }

    public void refreshUserData() {
        controller.refreshUserData();
        UserInputViewModel viewModel = presenter.getViewModel();
        fullNameField.setText(viewModel.getFullname());
        emailField.setText(viewModel.getEmail());
        if (viewModel.getWorkexperience()!=null) {
            workExperienceArea.setText(String.join("\n", viewModel.getWorkexperience()));
        }else {
            workExperienceArea.setText("");
        }
        if (viewModel.getEducation()!=null) {
            educationArea.setText(String.join("\n", viewModel.getEducation()));
        }else {
            educationArea.setText("");
        }
        if (viewModel.getSkills()!=null) {
            skillsArea.setText(String.join("\n", viewModel.getSkills()));
        }else {
            skillsArea.setText("");
        }
    }
}
