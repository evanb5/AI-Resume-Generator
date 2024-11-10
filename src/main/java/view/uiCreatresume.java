package view;

import com.github.models.inference.samples.BasicChatSample;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/*public class uiCreatresume extends JFrame {
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
    }*/
public class uiCreatresume {
    private JPanel panel;
    private JTextField nameField;
    private JTextField emailField;
    private JTextArea experienceArea;
    private JTextArea educationArea;
    private JTextArea skillsArea;
    private JButton submitButton;
    private JButton finishButton;
    private JTextArea chatArea;
    private BasicChatSample chatSample;

    public uiCreatresume(BasicChatSample chatSample) {
        this.chatSample = chatSample;
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Basic fields for resume data
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        experienceArea = new JTextArea(5, 20);
        educationArea = new JTextArea(5, 20);
        skillsArea = new JTextArea(5, 20);
        submitButton = new JButton("Submit");
        finishButton = new JButton("Finish");
        chatArea = new JTextArea(10, 30);
        chatArea.setEditable(false);

        // Add components to the panel
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Experience:"));
        panel.add(new JScrollPane(experienceArea));
        panel.add(new JLabel("Education:"));
        panel.add(new JScrollPane(educationArea));
        panel.add(new JLabel("Skills:"));
        panel.add(new JScrollPane(skillsArea));
        panel.add(submitButton);
        panel.add(finishButton);
        panel.add(new JScrollPane(chatArea));

        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });

        // Add action listener to the finish button
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFinish();
            }
        });
    }

    private String sanitizeInput(String input) {
        try {
            return URLEncoder.encode(input, StandardCharsets.UTF_8.toString());
        } catch (Exception e) {
            return ""; // Return empty if encoding fails
        }
    }

    private void handleSubmit() {
        // Collect data from the fields and encode them
        String name = sanitizeInput(nameField.getText().trim());
        String email = sanitizeInput(emailField.getText().trim());
        String experience = sanitizeInput(experienceArea.getText().trim());
        String education = sanitizeInput(educationArea.getText().trim());
        String skills = sanitizeInput(skillsArea.getText().trim());

        // Check that required fields are not empty
        if (name.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "Please fill in Name and Email fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Construct a prompt for generating a resume
        String resumePrompt = "Generate a professional resume based on the following details: " +
                "Name: " + name  +
                "Email: " + email +
                "Experience: " + experience +
                "Education: " + education +
                "Skills: " + skills;

        // Process the prompt with BasicChatSample
        String response = chatSample.processUserInput(resumePrompt);

        // Display the generated resume in chat area
        chatArea.setText("Generated Resume:\n" + response + "\n");
    }

    private void handleFinish() {
        try {
            chatSample.close(); // Finalize and save the PDF
            JOptionPane.showMessageDialog(panel, "PDF file 'chat_responses.pdf' has been saved successfully.", "Finish", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, "Error saving PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}