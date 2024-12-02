// view/BuildCVView.java
package view;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.build_cv.*;
import use_case.build_cv.BuildCVInputData;

public class BuildCVView extends JPanel implements PropertyChangeListener {
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
                       BuildCVViewModel viewModel) {
        this.viewManager = viewManager;
        this.controller = controller;
        buildCVViewModel = viewModel;
        buildCVViewModel.addPropertyChangeListener(this);

        // Initialize components
        jobDescriptionArea = new JTextArea(5, 20);
        cvTitleArea = new JTextArea(5,10);
        generateButton = new JButton("Generate Cover Letter");
        backButton = new JButton("Back");
        cvDisplayArea = new JTextArea(20, 50);
        cvDisplayArea.setEditable(false);
        messageLabel = new JLabel();


        // Layout components
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Job Description:"));
        add(new JScrollPane(jobDescriptionArea));
        add(new JLabel("Cover Letter Title:"));
        add(new JScrollPane(cvTitleArea));
        add(generateButton);
        add(backButton);
        add(new JLabel("Generated Cover Letter:"));
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final BuildCVState state = (BuildCVState) evt.getNewValue();
        cvDisplayArea.setText(state.getFormattedCV());
        messageLabel.setText(state.getMessage());
    }

    public void clearView() {
        jobDescriptionArea.setText("");
        cvTitleArea.setText("");
        messageLabel.setText("");
        cvDisplayArea.setText("");
    }
}
