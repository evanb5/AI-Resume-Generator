// view/LoginView.java
package view;

import javax.swing.*;
import java.awt.event.*;
import interface_adapter.login.*;
import session.UserSession;

public class LoginView extends JPanel {
    private ViewManager viewManager;
    private LoginController controller;
    private LoginPresenter presenter;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;
    private JLabel messageLabel;

    public LoginView(ViewManager viewManager, LoginController controller, LoginPresenter presenter) {
        this.viewManager = viewManager;
        this.controller = controller;
        this.presenter = presenter;

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        signupButton = new JButton("Signup");
        messageLabel = new JLabel();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(signupButton);
        add(messageLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.login(usernameField.getText(), new String(passwordField.getPassword()));
                LoginViewModel viewModel = presenter.getViewModel();
                messageLabel.setText(viewModel.getMessage());
                if (viewModel.isSuccess()) {
                    UserSession.getInstance().setCurrentUser(viewModel.getUser());
                    viewManager.showUserInputView();
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManager.showSignupView();
            }
        });
    }
}
