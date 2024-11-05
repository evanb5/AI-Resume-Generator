package com.github.models.inference.samples;

import java.util.Arrays;
import java.util.List;

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

        List<ChatRequestMessage> chatMessages = Arrays.asList(
                new ChatRequestSystemMessage("You are a helpful assistant."),
                new ChatRequestUserMessage("Tell me 3 jokes about trains")
        );

        ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(chatMessages);
        chatCompletionsOptions.setModel(model);

        ChatCompletions completions = client.complete(chatCompletionsOptions);

        for (var choice : completions.getChoices()) {
            System.out.printf("%s.%n", choice.getMessage().getContent());
        }
    }
}