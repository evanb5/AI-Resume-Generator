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
        return generateDocument("Generate a detailed CV for the following user:\n" + userInfo + "\nJob Description:\n" + jobDescription);
    }

    private String generateDocument(String userInput) {
        chatMessages.add(new ChatRequestUserMessage(userInput));

        ChatCompletionsOptions options = new ChatCompletionsOptions(chatMessages);
        options.setModel("gpt-4o-mini");

        ChatCompletions completions = client.complete(options);

        String assistantResponse = "";
        for (ChatChoice choice : completions.getChoices()) {
            assistantResponse = choice.getMessage().getContent();
            chatMessages.add(new ChatRequestAssistantMessage(assistantResponse));
        }

        return assistantResponse;
    }

    public String generateSuggestions(String userInfo, String jobDescription) {
        String userInput = "Provide suggestions to improve the resume of the following user for the job:\n" + userInfo + "\nJob Description:\n" + jobDescription;

        chatMessages.add(new ChatRequestUserMessage(userInput));

        ChatCompletionsOptions options = new ChatCompletionsOptions(chatMessages);
        options.setModel("gpt-4o-mini");

        ChatCompletions completions = client.complete(options);

        String assistantResponse = "";
        for (ChatChoice choice : completions.getChoices()) {
            assistantResponse = choice.getMessage().getContent();
            chatMessages.add(new ChatRequestAssistantMessage(assistantResponse));
        }

        return assistantResponse;
    }
}
