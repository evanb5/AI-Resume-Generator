// services/ChatGPTService.java
package services;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.*;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.Configuration;

import java.util.ArrayList;
import java.util.List;

public class ChatGPTService {
    private String apiKey = Configuration.getGlobalConfiguration().get("GITHUB_TOKEN");
    private String endpoint = "https://models.inference.ai.azure.com";
    private String deploymentName = "gpt-4o-mini";
    private final ChatCompletionsClient client;
    private final List<ChatRequestMessage> chatMessages;

    public ChatGPTService() {
        // Initialize chat client with the provided API key and endpoint
        this.client = new ChatCompletionsClientBuilder()
                .credential(new AzureKeyCredential(apiKey))
                .endpoint(endpoint)
                .buildClient();

        // Initialize conversation history with a system message
        this.chatMessages = new ArrayList<>();
        chatMessages.add(new ChatRequestSystemMessage("You are a helpful assistant that helps users create professional documents."));
    }

    public String generateResume(String userInfo, String jobDescription, int templateNumber) {
        String userInput = switch (templateNumber) {
            case 1 ->
                    "Generate a professional resume for the following user:\n" + userInfo + "\nJob Description:\n" + jobDescription;
            case 2 ->
                    "Generate a modern resume emphasizing skills and achievements for the following user:\n" + userInfo + "\nJob Description:\n" + jobDescription;
            case 3 ->
                    "Generate a creative resume focusing on design and layout for the following user:\n" + userInfo + "\nJob Description:\n" + jobDescription;
            default ->
                    "Generate a professional resume for the following user:\n" + userInfo + "\nJob Description:\n" + jobDescription;
        };
        // Debugging: Print the userInput to verify correctness
        System.out.println("Generated User Input for API:\n" + userInput);
        return generateDocument(userInput);
    }

    public String generateCV(String userInfo, String jobDescription) {
        String userInput = "Generate a detailed cover letter for the following user:\n" + userInfo + "\nJob Description:\n" + jobDescription;
        return generateDocument(userInput);
    }

    public String generateSuggestions(String userInfo,String insertedResume ,String jobDescription) {
        String userInput = "Generate a comprehensive and concise list of constructive and professional suggestions in bullet point format for a client with the following resume:\n" + insertedResume + "\n The suggestions should check if the client put all their strengths, " +
        "work experience, and education stated in the following description: \n" + userInfo +
                "\n The suggestions should also make sure that the resume is tailored towards the following job description:\n" + jobDescription;
        return generateDocument(userInput);
    }

    private String generateDocument(String userInput) {
        if (userInput == null || userInput.trim().isEmpty()) {
            return "Please enter a valid message.";
        }

        // Escape newline characters in userInput
        userInput = userInput.replace("\n", "\\n");

        // Add user message to conversation history
        ChatRequestUserMessage userMessage = new ChatRequestUserMessage(userInput);
        chatMessages.add(userMessage);

        // Set up chat options with the entire conversation history
        ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(chatMessages);
        chatCompletionsOptions.setModel(deploymentName);

        // Get response from Azure's Chat API
        ChatCompletions completions = client.complete(chatCompletionsOptions);
        String assistantResponse = "";

        // Process response and update the conversation history
        for (var choice : completions.getChoices()) {
            assistantResponse = choice.getMessage().getContent();

            // Convert the assistant response to a ChatRequestAssistantMessage and add to conversation history
            ChatRequestAssistantMessage assistantMessage = new ChatRequestAssistantMessage(assistantResponse);
            chatMessages.add(assistantMessage);
        }

        return assistantResponse;
     }

}
