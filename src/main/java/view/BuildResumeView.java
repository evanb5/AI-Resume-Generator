// view/BuildResumeView.java
package view;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

import interface_adapter.build_resume.*;
import interface_adapter.login.LoginState;
import interface_adapter.signup.SignupState;
import session.UserSession;
import use_case.build_resume.BuildResumeInputData;

public class BuildResumeView extends JPanel implements PropertyChangeListener {
    private ViewManager viewManager;
    private BuildResumeController controller;
    private BuildResumeViewModel buildResumeViewModel;

    private JTextArea jobDescriptionArea;
    private JComboBox<String> templateComboBox;
    private JButton generateButton;
    private JButton backButton;
    private JTextArea resumeDisplayArea;
    private JLabel messageLabel;

    public BuildResumeView(ViewManager viewManager, BuildResumeController controller,
                           BuildResumeViewModel buildResumeViewModel) {
        this.viewManager = viewManager;
        this.controller = controller;
        this.buildResumeViewModel = buildResumeViewModel;
        this.buildResumeViewModel.addPropertyChangeListener(this);

        // Initialize components
        jobDescriptionArea = new JTextArea(5, 20);
        templateComboBox = new JComboBox<>(new String[]{"Professional Resume", "Modern Resume prioritizing Skills", "Creative Resume prioritizing Design/Layout"});
        generateButton = new JButton("Generate Resume");
        backButton = new JButton("Back");
        resumeDisplayArea = new JTextArea(20, 50);
        resumeDisplayArea.setEditable(false);
        messageLabel = new JLabel();

        // Layout components
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Job Description:"));
        add(new JScrollPane(jobDescriptionArea));
        add(new JLabel("Select Template:"));
        add(templateComboBox);
        add(generateButton);
        add(backButton);
        add(new JLabel("Generated Resume:"));
        add(new JScrollPane(resumeDisplayArea));
        add(messageLabel);

        // Add action listeners
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String jobDescription = jobDescriptionArea.getText();
                String templateChoice = (String) templateComboBox.getSelectedItem();

                int templateNumber = mapTemplateChoiceToNumber(templateChoice);

                BuildResumeInputData inputData = new BuildResumeInputData( jobDescription, templateNumber);
                controller.buildResume(inputData);
            }

            // Helper method to map template names to numbers
            private int mapTemplateChoiceToNumber(String templateChoice) {
                return switch (templateChoice) {
                    case "Professional Resume" -> 1;
                    case "Modern Resume prioritizing Skills" -> 2;
                    case "Creative Resume prioritizing Design/Layout" -> 3;
                    default -> 1; // Default to Template 1 if unrecognized
                };
            }

        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManager.showUserInputView();
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final BuildResumeState state = (BuildResumeState) evt.getNewValue();
        setFields(state);
    }

    //update fields
    private void setFields(BuildResumeState state) {
        resumeDisplayArea.setText(state.getFormattedResume());
        messageLabel.setText(state.getMessage());
    }

    public void clearView() {
        jobDescriptionArea.setText("");
        resumeDisplayArea.setText("");
        messageLabel.setText("");
        templateComboBox.setSelectedIndex(0);
    }

}
