package view;

import interface_adapter.resume_history.ResumeHistoryController;
import interface_adapter.resume_history.ResumeHistoryPresenter;
import interface_adapter.resume_history.ResumeHistoryViewModel;
import use_case.resume_history.ResumeHistoryInputData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PastResumesView extends JPanel {
    private ViewManager viewManager;
    private ResumeHistoryController controller;
    private ResumeHistoryPresenter presenter;
    private ResumeHistoryViewModel viewModel;
    private int resumes;

    private JButton backButton;
    private JTextArea displayArea;

    public PastResumesView(ViewManager viewManager, ResumeHistoryController controller, ResumeHistoryPresenter presenter) {
        this.viewManager = viewManager;
        this.controller = controller;
        this.presenter = presenter;

        // Initialize UI Components
        backButton = new JButton("Back");
        displayArea = new JTextArea(20, 50);
        displayArea.setEditable(false);

        // Fetch initial resume data
        controller.fetchResumeHistory(new ResumeHistoryInputData(-4));
        ResumeHistoryViewModel viewModel = presenter.getViewModel();
        resumes = viewModel.getResumes();
        // Layout and display logic
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
                JButton resumeButton = new JButton("Resume"+(i+1));
                int index = i;
                resumeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.fetchResumeHistory(new ResumeHistoryInputData(index));
                        displayArea.setText(presenter.getViewModel().getResumeContent());
                    }
                });
                buttonPanel.add(resumeButton);
            }
            add(buttonPanel);
            add(new JScrollPane(displayArea));
            add(backButton);
            displayArea.setText("");
        }

        // Action Listener for Back Button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManager.showHistoryView();
            }
        });
    }

    public void refreshResumes() {
        removeAll();
        controller.fetchResumeHistory(new ResumeHistoryInputData(-4));
        ResumeHistoryViewModel viewModel = presenter.getViewModel();
        resumes = viewModel.getResumes();

        if (resumes==0) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            displayArea.setText("No resumes found.");
            add(displayArea);
            add(backButton);
        } else {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

            for (int i = 0; i < resumes; i++) {
                JButton resumeButton = new JButton("Resume"+(i+1));
                int index = i;
                resumeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.fetchResumeHistory(new ResumeHistoryInputData(index));
                        displayArea.setText(presenter.getViewModel().getResumeContent());
                    }
                });
                buttonPanel.add(resumeButton);
            }
            add(buttonPanel);
            add(new JScrollPane(displayArea));
            add(backButton);
            displayArea.setText("");
        }
    }
}
