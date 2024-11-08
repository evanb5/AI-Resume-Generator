import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class uiCreatresume extends JFrame {
    private JTextField nameField, emailField, universityField, gpaField;
    private JTextArea competitionsArea, internshipsArea, extracurricularsArea, skillsArea, interestsArea, othersArea;
    private JButton submitButton;

    public uiCreatresume() {
        setTitle("Extended Digital Form");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with a scrollable area for extended content
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Adding fields for Personal Information
        mainPanel.add(createSectionLabel("Personal Information"));
        nameField = createTextField("Name:", mainPanel);
        emailField = createTextField("Email:", mainPanel);

        // Adding fields for Education
        mainPanel.add(createSectionLabel("Education"));
        universityField = createTextField("University:", mainPanel);
        gpaField = createTextField("GPA:", mainPanel);
        competitionsArea = createTextArea("Competitions/Awards:", mainPanel);

        // Adding fields for Employment Experience
        mainPanel.add(createSectionLabel("Employment Experience"));
        internshipsArea = createTextArea("Past Internships:", mainPanel);

        // Adding fields for Extracurricular Activities
        mainPanel.add(createSectionLabel("Extracurricular Activities"));
        extracurricularsArea = createTextArea("Clubs/Volunteering:", mainPanel);

        // Adding fields for Skills
        mainPanel.add(createSectionLabel("Skills"));
        skillsArea = createTextArea("Skills:", mainPanel);

        // Adding fields for Interests
        mainPanel.add(createSectionLabel("Interests"));
        interestsArea = createTextArea("Interests:", mainPanel);

        // Adding fields for Other Information
        mainPanel.add(createSectionLabel("Other Information"));
        othersArea = createTextArea("Other:", mainPanel);

        // Submit button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitAction());
        mainPanel.add(submitButton);

        // Wrapping everything in a JScrollPane for scrolling
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane);
    }

    // Helper method to create a JLabel for section headers
    private JLabel createSectionLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    // Helper method to create a text field with a label
    private JTextField createTextField(String labelText, JPanel panel) {
        panel.add(new JLabel(labelText));
        JTextField textField = new JTextField(30);
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, textField.getPreferredSize().height));
        panel.add(textField);
        return textField;
    }

    // Helper method to create a text area with a label
    private JTextArea createTextArea(String labelText, JPanel panel) {
        panel.add(new JLabel(labelText));
        JTextArea textArea = new JTextArea(3, 30);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);
        return textArea;
    }

    private class SubmitAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String email = emailField.getText();
            String university = universityField.getText();
            String gpa = gpaField.getText();
            String competitions = competitionsArea.getText();
            String internships = internshipsArea.getText();
            String extracurriculars = extracurricularsArea.getText();
            String skills = skillsArea.getText();
            String interests = interestsArea.getText();
            String others = othersArea.getText();

            // Simple validation
            if (name.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in the required fields (Name and Email).", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Output the collected data (could be saved to a file or database in a real app)
                String message = "Form Submitted:\n\n" +
                        "Name: " + name + "\n" +
                        "Email: " + email + "\n" +
                        "University: " + university + "\n" +
                        "GPA: " + gpa + "\n" +
                        "Competitions: " + competitions + "\n" +
                        "Internships: " + internships + "\n" +
                        "Extracurriculars: " + extracurriculars + "\n" +
                        "Skills: " + skills + "\n" +
                        "Interests: " + interests + "\n" +
                        "Others: " + others;
                JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            uiCreatresume form = new uiCreatresume();
            form.setVisible(true);
        });
    }
}