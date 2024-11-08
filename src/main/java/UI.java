import javax.swing.*;
public class UI {
    public static void main(String[] args) {
        JPanel firstNamePanel = new JPanel();
        firstNamePanel.add(new JLabel("User Name"));
        firstNamePanel.add(new JTextField(10));
        JPanel lastNamePanel = new JPanel();
        lastNamePanel.add(new JLabel("Password:"));
        lastNamePanel.add(new JTextField(10));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("log in"));
        buttonPanel.add(new JButton("Cancel"));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(firstNamePanel);
        mainPanel.add(lastNamePanel);
        mainPanel.add(buttonPanel);
        JFrame frame = new JFrame("LOG IN");
        frame.setContentPane(mainPanel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
