// view/GiveSuggestionsView.java
package view;

import javax.swing.*;
import java.awt.event.*;
import interface_adapter.give_suggestions.*;
import session.UserSession;
import use_case.give_suggestions.GiveSuggestionsInputData;

public class GiveSuggestionsView extends JPanel {
    private ViewManager viewManager;
    private GiveSuggestionsController controller;
    private GiveSuggestionsViewModel giveSuggestionsViewModel;

    private JTextArea insertedResumeArea;
    private JButton getSuggestionsButton;
    private JButton backButton;
    private JTextArea suggestionsDisplayArea;
    private JLabel messageLabel;
    private JTextArea jobDescriptionArea;

    public GiveSuggestionsView(ViewManager viewManager, GiveSuggestionsController controller,
                               GiveSuggestionsViewModel giveSuggestionsViewModel) {
        this.viewManager = viewManager;
        this.controller = controller;
        this.giveSuggestionsViewModel = giveSuggestionsViewModel;

        // Initialize components
        insertedResumeArea = new JTextArea(20, 50);
        getSuggestionsButton = new JButton("Get Suggestions");
        backButton = new JButton("Back");
        suggestionsDisplayArea = new JTextArea(20, 50);
        suggestionsDisplayArea.setEditable(false);
        messageLabel = new JLabel();
        jobDescriptionArea = new JTextArea(5, 20);

        // Layout components
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Inserted Resume"));
        add(new JScrollPane(insertedResumeArea));
        add(new JLabel("job Description"));
        add(new JScrollPane(jobDescriptionArea));
        add(getSuggestionsButton);
        add(backButton);
        add(new JLabel("Suggestions:"));
        add(new JScrollPane(suggestionsDisplayArea));
        add(messageLabel);

        // Add action listeners
        getSuggestionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String insertedResume = insertedResumeArea.getText();
                String jobDescription = jobDescriptionArea.getText();

                GiveSuggestionsInputData inputData = new GiveSuggestionsInputData(insertedResume, jobDescription);
                controller.giveSuggestions(inputData);

                // Update view
                suggestionsDisplayArea.setText(giveSuggestionsViewModel.getSuggestions());
                messageLabel.setText(giveSuggestionsViewModel.getMessage());
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
