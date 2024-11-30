// view/BuildCVView.java
package view;

import javax.swing.*;
import java.awt.event.*;
import interface_adapter.build_cv.*;
import session.UserSession;
import use_case.build_cv.BuildCVInputData;

public class BuildCVView extends JPanel {
    private ViewManager viewManager;
    private BuildCVController controller;
    private BuildCVPresenter presenter;

    private JTextArea jobDescriptionArea;
    private JComboBox<String> templateComboBox;
    private JButton generateButton;
    private JButton backButton;
    private JTextArea cvDisplayArea;
    private JLabel messageLabel;
    private JTextArea  cvTitleArea;

    public BuildCVView(ViewManager viewManager, BuildCVController controller, BuildCVPresenter presenter) {
        this.viewManager = viewManager;
        this.controller = controller;
        this.presenter = presenter;

        // Initialize components
        jobDescriptionArea = new JTextArea(5, 20);
        cvTitleArea = new JTextArea(5,20);
        templateComboBox = new JComboBox<>(new String[]{"Template A", "Template B", "Template C"});
        generateButton = new JButton("Generate CV");
        backButton = new JButton("Back");
        cvDisplayArea = new JTextArea(20, 50);
        cvDisplayArea.setEditable(false);
        messageLabel = new JLabel();


        // Layout components
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Job Description:"));
        add(new JScrollPane(jobDescriptionArea));
        add(new JLabel("Select Template:"));
        add(templateComboBox);
        add(new JLabel("CV Title:"));
        add(cvTitleArea);
        add(generateButton);
        add(backButton);
        add(new JLabel("Generated CV:"));
        add(new JScrollPane(cvDisplayArea));
        add(messageLabel);

        // Add action listeners
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String jobDescription = jobDescriptionArea.getText();
                String templateChoice = (String) templateComboBox.getSelectedItem();
                String cvTitle = cvTitleArea.getText();

                BuildCVInputData inputData = new BuildCVInputData(jobDescription, templateChoice, cvTitle);
                controller.buildCV(inputData);

                // Update view
                BuildCVViewModel viewModel = presenter.getViewModel();
                cvDisplayArea.setText(viewModel.getFormattedCV());
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
