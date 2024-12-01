// view/BuildCVView.java
package view;

import javax.swing.*;
import java.awt.event.*;
import interface_adapter.build_cv.*;
import use_case.build_cv.BuildCVInputData;

public class BuildCVView extends JPanel {
    private ViewManager viewManager;
    private BuildCVController controller;
    private BuildCVViewModel buildCVViewModel;

    private JTextArea jobDescriptionArea;
    private JButton generateButton;
    private JButton backButton;
    private JTextArea cvDisplayArea;
    private JLabel messageLabel;
    private JTextArea  cvTitleArea;

    public BuildCVView(ViewManager viewManager, BuildCVController controller,
                       BuildCVViewModel buildCVViewModel) {
        this.viewManager = viewManager;
        this.controller = controller;
        this.buildCVViewModel = buildCVViewModel;

        // Initialize components
        jobDescriptionArea = new JTextArea(5, 20);
        cvTitleArea = new JTextArea(5,20);
        generateButton = new JButton("Generate CV");
        backButton = new JButton("Back");
        cvDisplayArea = new JTextArea(20, 50);
        cvDisplayArea.setEditable(false);
        messageLabel = new JLabel();


        // Layout components
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Job Description:"));
        add(new JScrollPane(jobDescriptionArea));
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
                String cvTitle = cvTitleArea.getText();

                BuildCVInputData inputData = new BuildCVInputData(jobDescription, cvTitle);
                controller.buildCV(inputData);

                // Update view
                cvDisplayArea.setText(buildCVViewModel.getFormattedCV());
                messageLabel.setText(buildCVViewModel.getMessage());
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
