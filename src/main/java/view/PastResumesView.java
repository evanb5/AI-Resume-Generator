package view;

import interface_adapter.resume_history.ResumeHistoryController;
import interface_adapter.resume_history.ResumeHistoryPresenter;
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

    private JButton backButton;
    private JTextArea displayArea;
    private JButton nextButton;
    private JButton previousButton;

    public PastResumesView(ViewManager viewManager, ResumeHistoryController controller, ResumeHistoryPresenter presenter) {
        this.viewManager = viewManager;
        this.controller = controller;
        this.presenter = presenter;

        // Initialize UI Components
        backButton = new JButton("Back");
        nextButton = new JButton("Next");
        previousButton = new JButton("Previous");
        displayArea = new JTextArea(20, 50);
        displayArea.setEditable(false);

        // Fetch initial resume data
        controller.fetchResumeHistory(new ResumeHistoryInputData(-4));
        List<String> resumeTitles = presenter.getViewModel().getResumeTitles() != null
                ? new ArrayList<>(presenter.getViewModel().getResumeTitles())
                : new ArrayList<>(); // Avoid null pointer exception

        // Layout and display logic
        if (resumeTitles.isEmpty()) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            displayArea.setText("No resumes found.");
            add(displayArea);
            add(backButton);
        } else {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            add(backButton);
            add(nextButton);
            add(previousButton);

            for (int i = 0; i < resumeTitles.size(); i++) {
                JButton resumeButton = new JButton(resumeTitles.get(i));
                int index = i;
                resumeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.fetchResumeHistory(new ResumeHistoryInputData(index));
                        displayArea.setText(presenter.getViewModel().getResumeContent());
                    }
                });
                add(resumeButton);
            }
            add(new JScrollPane(displayArea));
        }

        // Action Listener for Back Button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManager.showUserInputView();
            }
        });

        // Action Listener for Next Button
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.fetchResumeHistory(new ResumeHistoryInputData(-3));
                displayArea.setText(presenter.getViewModel().getResumeContent());
            }
        });

        // Action Listener for Previous Button
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.fetchResumeHistory(new ResumeHistoryInputData(-2));
                displayArea.setText(presenter.getViewModel().getResumeContent());
            }
        });
    }

    public void refreshResumes() {
        removeAll();
        controller.fetchResumeHistory(new ResumeHistoryInputData(-4));
        List<String> resumeTitles = presenter.getViewModel().getResumeTitles() != null
                ? new ArrayList<>(presenter.getViewModel().getResumeTitles())
                : new ArrayList<>(); // Avoid null pointer exception

        if (resumeTitles.isEmpty()) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            displayArea.setText("No resumes found.");
            add(displayArea);
            add(backButton);
        } else {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            add(backButton);
            add(nextButton);
            add(previousButton);

            for (int i = 0; i < resumeTitles.size(); i++) {
                JButton resumeButton = new JButton(resumeTitles.get(i));
                int index = i;
                resumeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.fetchResumeHistory(new ResumeHistoryInputData(index));
                        displayArea.setText(presenter.getViewModel().getResumeContent());
                    }
                });
                add(resumeButton);
            }
            add(new JScrollPane(displayArea));
            displayArea.setText("");
        }
    }
}
