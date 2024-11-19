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

    public String generateResume(String userInfo, String jobDescription) {
        String userInput = "Generate a professional resume for the following user:\n" + userInfo + "\nJob Description:\n" + jobDescription;
        return generateDocument(userInput);
    }

    public String generateCV(String userInfo, String jobDescription) {
        String userInput = "Generate a detailed CV for the following user:\n" + userInfo + "\nJob Description:\n" + jobDescription;
        return generateDocument(userInput);
    }

    public String generateSuggestions(String userInfo, String jobDescription) {
        String userInput = "Based on the following user information and job description, provide suggestions for improvement:\n" + userInfo + "\nJob Description:\n" + jobDescription;
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
