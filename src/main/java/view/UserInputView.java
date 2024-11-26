// view/UserInputView.java
package view;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;
import interface_adapter.user_input.*;
import entity.User;
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
    private JButton pastResumesButton; // New button for "Past Resumes"
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
        pastResumesButton = new JButton("Past Resumes"); // Initialize the new button
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
        add(pastResumesButton); // Add the new button to the layout
        add(logoutButton);
        add(messageLabel);

        // Populate current user information
        User currentUser = UserSession.getInstance().getCurrentUser();
        if (currentUser != null) {
            fullNameField.setText(currentUser.getFullName());
            emailField.setText(currentUser.getEmail());
            workExperienceArea.setText(String.join("\n", currentUser.getWorkExperience()));
            educationArea.setText(String.join("\n", currentUser.getEducation()));
            skillsArea.setText(String.join("\n", currentUser.getSkills()));
        }

        // Add action listeners
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = UserSession.getInstance().getCurrentUser();
                user.setFullName(fullNameField.getText());
                user.setEmail(emailField.getText());
                user.setWorkExperience(Arrays.asList(workExperienceArea.getText().split("\n")));
                user.setEducation(Arrays.asList(educationArea.getText().split("\n")));
                user.setSkills(Arrays.asList(skillsArea.getText().split("\n")));

                UserInputData inputData = new UserInputData(user);
                controller.updateUserData(inputData);

                // Update view
                UserInputViewModel viewModel = presenter.getViewModel();
                messageLabel.setText(viewModel.getMessage());
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
        });}}

        // Add action listener for the "Past Resumes" button
//        pastResumesButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                viewManager.showPastResumesView(); // Placeholder for showing past resumes
//            }
//        });
//    }
//}
