package app;

import view.uiCreatresume;
import com.github.models.inference.samples.BasicChatSample;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;

public class MainWithinMemory {

    private JFrame frame;
    private BasicChatSample chatSample;
    private uiCreatresume createResumePage;

    public MainWithinMemory() {
        frame = new JFrame("Application");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            // Initialize BasicChatSample
            chatSample = new BasicChatSample();

            // Initialize UI component for creating a resume
            createResumePage = new uiCreatresume(chatSample);

            // Display the create resume page initially
            frame.add(createResumePage.getPanel());
            frame.setVisible(true);

        } catch (FileNotFoundException e) {
            System.err.println("Error initializing BasicChatSample: " + e.getMessage());
            JOptionPane.showMessageDialog(frame, "An error occurred while initializing the chat feature. Please check file permissions.", "Error", JOptionPane.ERROR_MESSAGE);
            // Optionally, you can exit or handle this case differently if BasicChatSample is critical to the app
            System.exit(1);
        }
    }

    public static void main(String[] args) {

        new MainWithinMemory();

    }
}
