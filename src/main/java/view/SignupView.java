// view/SignupView.java
package view;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

import interface_adapter.login.LoginState;
import interface_adapter.signup.*;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    private ViewManager viewManager;
    private SignupController controller;
    private SignupViewModel viewModel;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JButton signupButton;
    private JButton backButton;
    private JLabel messageLabel;

    public SignupView(ViewManager viewManager, SignupViewModel viewModel) {
        this.viewManager = viewManager;
        this.controller = controller;
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);

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
                if (e.getSource() == signupButton) {
                    updateCurrentState();
                    controller.signup(usernameField.getText(), new String(passwordField.getPassword()), emailField.getText());
                    messageLabel.setText(viewModel.getMessage());
                    if (viewModel.isSuccess()) {
                        viewManager.showLoginView();
                    }
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
    private void updateCurrentState() {
        final SignupState currentState = viewModel.getState();
        currentState.setEmail(emailField.getText());
        currentState.setPassword(Arrays.toString(passwordField.getPassword()));
        currentState.setUserName(usernameField.getText());
        viewModel.setState(currentState);
    }

    public void setSignupController(SignupController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SignupState state = (SignupState) evt.getNewValue();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }
}
