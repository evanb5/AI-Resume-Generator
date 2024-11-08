import javax.swing.*;
public class uiTOPPAGE {
    public static void main(String[] args) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(new JButton("log out"));
        buttonPanel.add(new JButton("Create new Resume"));
        buttonPanel.add(new JButton("Saved Resume"));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(buttonPanel);
        JFrame frame = new JFrame("TOP PAGE");
        frame.setContentPane(mainPanel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
