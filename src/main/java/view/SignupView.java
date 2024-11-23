// view/SignupView.java
package view;

import javax.swing.*;
import java.awt.event.*;
import interface_adapter.signup.*;

public class SignupView extends JPanel {
    private ViewManager viewManager;
    private SignupController controller;
    private SignupPresenter presenter;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JButton signupButton;
    private JButton backButton;
    private JLabel messageLabel;

    public SignupView(ViewManager viewManager, SignupController controller, SignupPresenter presenter) {
        this.viewManager = viewManager;
        this.controller = controller;
        this.presenter = presenter;

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        emailField = new JTextField(20);
        signupButton = new JButton("Sign up");
        backButton = new JButton("Back");
        messageLabel = new JLabel();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Email:"));
        add(emailField);
        add(signupButton);
        add(backButton);
        add(messageLabel);

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.signup(usernameField.getText(), new String(passwordField.getPassword()), emailField.getText());
                SignupViewModel viewModel = presenter.getViewModel();
                messageLabel.setText(viewModel.getMessage());
                if (viewModel.isSuccess()) {
                    viewManager.showLoginView();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManager.showLoginView();
            }
        });
    }
}
