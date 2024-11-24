// services/ChatGPTService.java
package services;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.*;
import com.azure.core.credential.AzureKeyCredential;

import java.util.ArrayList;
import java.util.List;

public class ChatGPTService {
    private ChatCompletionsClient client;
    private List<ChatRequestMessage> chatMessages;

    public ChatGPTService(String apiKey, String endpoint) {
        this.client = new ChatCompletionsClientBuilder()
                .credential(new AzureKeyCredential(apiKey))
                .endpoint(endpoint)
                .buildClient();

        this.chatMessages = new ArrayList<>();
        chatMessages.add(new ChatRequestSystemMessage("You are a helpful assistant that helps users create professional documents."));
    }

    public String generateResume(String userInfo, String jobDescription) {
        return generateDocument("Generate a professional resume for the following user:\n" + userInfo + "\nJob Description:\n" + jobDescription);
    }

    public String generateCV(String userInfo, String jobDescription) {
        return generateDocument("Generate a detailed and concise professional CV for a client with the following information:\n" + userInfo + "\n Tailor the CV for a job with the following description:\n" + jobDescription + " The CV must contain the client's name, email, and highlight the user's strengths, work experience, and education");
    }

    private String generateDocument(String userInput) {
        //TODO
        return null;
    }

    public String generateSuggestions(String userInfo, String jobDescription, String insertedResume) {
        return generateDocument("Generate a comprehensive and concise list of constructive and professional suggestions in bullet point format for a client with the following resume:\n" + insertedResume + "\n The suggestions should check if the client put all their strengths, " +
                "work experience, and education stated in the following description: \n" + userInfo +
                "\n The suggestions should also make sure that the resume is tailored towards the following job description:\n" + jobDescription);
    }
}
