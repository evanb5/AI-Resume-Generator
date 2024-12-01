package view;

import interface_adapter.resume_history.ResumeHistoryController;
import interface_adapter.resume_history.ResumeHistoryViewModel;
import use_case.resume_history.ResumeHistoryInputData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PastResumesView extends JPanel {
    private final ViewManager viewManager;
    private final ResumeHistoryController controller;
    private final ResumeHistoryViewModel resumeHistoryViewModel;
    private int resumes;

    private final JButton backButton;
    private final JTextArea displayArea;

    public PastResumesView(ViewManager viewManager, ResumeHistoryController controller, ResumeHistoryViewModel resumeHistoryViewModel) {
        this.viewManager = viewManager;
        this.controller = controller;
        this.resumeHistoryViewModel = resumeHistoryViewModel;

        backButton = new JButton("Back");
        displayArea = new JTextArea(20, 50);
        displayArea.setEditable(false);

        // Fetch initial resume data
        controller.fetchResumeHistory(new ResumeHistoryInputData(-4));
        resumes = resumeHistoryViewModel.getResumes();

        // Layout logic
        if (resumes == 0) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            displayArea.setText("No resumes found.");
            add(displayArea);
            add(backButton);
        } else {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

            for (int i = 0; i < resumes; i++) {
                JButton resumeButton = new JButton("Resume " + (i + 1));
                int index = i;
                resumeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.fetchResumeHistory(new ResumeHistoryInputData(index));
                        displayArea.setText(resumeHistoryViewModel.getResumeContent());
                    }
                });
                buttonPanel.add(resumeButton);
            }
            add(buttonPanel);
            add(new JScrollPane(displayArea));
            add(backButton);
        }

        backButton.addActionListener(e -> viewManager.showHistoryView());
    }

    public void refreshResumes() {
        removeAll();
        controller.fetchResumeHistory(new ResumeHistoryInputData(-4));
        resumes = resumeHistoryViewModel.getResumes();

        if (resumes == 0) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            displayArea.setText("No resumes found.");
            add(displayArea);
            add(backButton);
        } else {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

            for (int i = 0; i < resumes; i++) {
                JButton resumeButton = new JButton("Resume " + (i + 1));
                int index = i;
                resumeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.fetchResumeHistory(new ResumeHistoryInputData(index));
                        displayArea.setText(resumeHistoryViewModel.getResumeContent());
                    }
                });
                buttonPanel.add(resumeButton);
            }
            add(buttonPanel);
            add(new JScrollPane(displayArea));
            add(backButton);
        }
    }
}
