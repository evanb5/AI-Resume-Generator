// view/BuildResumeView.java
package view;

import javax.swing.*;
import java.awt.event.*;
import interface_adapter.build_resume.*;
import entity.User;
import session.UserSession;
import use_case.build_resume.BuildResumeInputData;

public class BuildResumeView extends JPanel {
    private ViewManager viewManager;
    private BuildResumeController controller;
    private BuildResumePresenter presenter;

    private JTextArea jobDescriptionArea;
    private JComboBox<String> templateComboBox;
    private JButton generateButton;
    private JButton backButton;
    private JTextArea resumeDisplayArea;
    private JLabel messageLabel;

    public BuildResumeView(ViewManager viewManager, BuildResumeController controller, BuildResumePresenter presenter) {
        this.viewManager = viewManager;
        this.controller = controller;
        this.presenter = presenter;

        // Initialize components
        jobDescriptionArea = new JTextArea(5, 20);
        templateComboBox = new JComboBox<>(new String[]{"Template 1", "Template 2", "Template 3"});
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
                User user = UserSession.getInstance().getCurrentUser();

                BuildResumeInputData inputData = new BuildResumeInputData(user, jobDescription, templateChoice);
                controller.buildResume(inputData);

                // Update view
                BuildResumeViewModel viewModel = presenter.getViewModel();
                resumeDisplayArea.setText(viewModel.getFormattedResume());
                messageLabel.setText(viewModel.getMessage());
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManager.showUserInputView();
            }
        });
    }
}
