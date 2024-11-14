// view/GiveSuggestionsView.java
package view;

import javax.swing.*;
import java.awt.event.*;
import interface_adapter.give_suggestions.*;
import entity.User;
import session.UserSession;
import use_case.give_suggestions.GiveSuggestionsInputData;

public class GiveSuggestionsView extends JPanel {
    private ViewManager viewManager;
    private GiveSuggestionsController controller;
    private GiveSuggestionsPresenter presenter;

    private JTextArea jobDescriptionArea;
    private JButton getSuggestionsButton;
    private JButton backButton;
    private JTextArea suggestionsDisplayArea;
    private JLabel messageLabel;

    public GiveSuggestionsView(ViewManager viewManager, GiveSuggestionsController controller, GiveSuggestionsPresenter presenter) {
        this.viewManager = viewManager;
        this.controller = controller;
        this.presenter = presenter;

        // Initialize components
        jobDescriptionArea = new JTextArea(5, 20);
        getSuggestionsButton = new JButton("Get Suggestions");
        backButton = new JButton("Back");
        suggestionsDisplayArea = new JTextArea(20, 50);
        suggestionsDisplayArea.setEditable(false);
        messageLabel = new JLabel();

        // Layout components
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Job Description:"));
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
                String jobDescription = jobDescriptionArea.getText();
                User user = UserSession.getInstance().getCurrentUser();

                GiveSuggestionsInputData inputData = new GiveSuggestionsInputData(user, jobDescription);
                controller.giveSuggestions(inputData);

                // Update view
                GiveSuggestionsViewModel viewModel = presenter.getViewModel();
                suggestionsDisplayArea.setText(viewModel.getSuggestions());
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
