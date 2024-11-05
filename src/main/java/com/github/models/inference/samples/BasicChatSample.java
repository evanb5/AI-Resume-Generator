package com.github.models.inference.samples;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.ChatCompletions;
import com.azure.ai.inference.models.ChatCompletionsOptions;
import com.azure.ai.inference.models.ChatRequestMessage;
import com.azure.ai.inference.models.ChatRequestSystemMessage;
import com.azure.ai.inference.models.ChatRequestUserMessage;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.Configuration;

public final class BasicChatSample {
    /**
     * @param args Unused. Arguments to the program.
     */
    public static void main(String[] args) {
        String key = Configuration.getGlobalConfiguration().get("GITHUB_TOKEN");

        String endpoint = "https://models.inference.ai.azure.com";
        String model = "gpt-4o-mini";

        ChatCompletionsClient client = new ChatCompletionsClientBuilder()
                .credential(new AzureKeyCredential(key))
                .endpoint(endpoint)
                .buildClient();

        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ask a question (type 'exit' to quit):");

        while (true) {
            // Get user input
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            if ("exit".equalsIgnoreCase(userInput)) {
                System.out.println("Exiting chat.");
                break;
            }

            // Prepare messages
            List<ChatRequestMessage> chatMessages = Arrays.asList(
                    new ChatRequestSystemMessage("You are a helpful assistant."),
                    new ChatRequestUserMessage(userInput)
            );

            // Set up chat options with the user's input
            ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(chatMessages);
            chatCompletionsOptions.setModel(model);

            // Get response
            ChatCompletions completions = client.complete(chatCompletionsOptions);

            // Print all response messages (in case there are multiple choices)
            completions.getChoices().forEach(choice -> {
                System.out.printf("Assistant: %s%n", choice.getMessage().getContent());
            });
        }

        scanner.close();
    }
}