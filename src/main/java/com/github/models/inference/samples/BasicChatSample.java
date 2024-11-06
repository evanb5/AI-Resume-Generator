package com.github.models.inference.samples;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.ChatCompletions;
import com.azure.ai.inference.models.ChatCompletionsOptions;
import com.azure.ai.inference.models.ChatRequestMessage;
import com.azure.ai.inference.models.ChatRequestSystemMessage;
import com.azure.ai.inference.models.ChatRequestUserMessage;
import com.azure.ai.inference.models.ChatRequestAssistantMessage;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.Configuration;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

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

        // Initialize PDF document and message history
        try {
            PdfWriter writer = new PdfWriter("chat_responses.pdf");
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            // Initialize conversation history with the system message
            List<ChatRequestMessage> chatMessages = new ArrayList<>();
            chatMessages.add(new ChatRequestSystemMessage("You are a helpful assistant."));

            while (true) {
                // Get user input
                System.out.print("You: ");
                String userInput = scanner.nextLine();

                if ("exit".equalsIgnoreCase(userInput)) {
                    System.out.println("Exiting chat.");
                    break;
                }

                // Add user message to conversation history
                ChatRequestUserMessage userMessage = new ChatRequestUserMessage(userInput);
                chatMessages.add(userMessage);

                // Set up chat options with the entire conversation history
                ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(chatMessages);
                chatCompletionsOptions.setModel(model);

                // Get response
                ChatCompletions completions = client.complete(chatCompletionsOptions);

                // Print response and add to PDF
                for (var choice : completions.getChoices()) {
                    String assistantResponse = choice.getMessage().getContent();
                    System.out.printf("Assistant: %s%n", assistantResponse);

                    // Add response to PDF
                    document.add(new Paragraph("You: " + userInput));
                    document.add(new Paragraph("Assistant: " + assistantResponse));
                    document.add(new Paragraph("")); // Add space between interactions

                    // Convert the assistant response to a ChatRequestAssistantMessage and add to conversation history
                    ChatRequestAssistantMessage assistantMessage = new ChatRequestAssistantMessage(assistantResponse);
                    chatMessages.add(assistantMessage);
                }
            }

            // Close the document
            document.close();
            System.out.println("PDF file 'chat_responses.pdf' created with chat history.");
        } catch (FileNotFoundException e) {
            System.err.println("Error creating PDF document: " + e.getMessage());
        }

        scanner.close();
    }
}
