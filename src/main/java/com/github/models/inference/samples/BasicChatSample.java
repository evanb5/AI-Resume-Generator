package com.github.models.inference.samples;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

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
    private final ChatCompletionsClient client;
    private final List<ChatRequestMessage> chatMessages;
    private final Document pdfDocument;

    public BasicChatSample() throws FileNotFoundException {
        String key = Configuration.getGlobalConfiguration().get("GITHUB_TOKEN");
        String endpoint = "https://models.inference.ai.azure.com";
        String model = "gpt-4o-mini";

        // Initialize chat client
        this.client = new ChatCompletionsClientBuilder()
                .credential(new AzureKeyCredential(key))
                .endpoint(endpoint)
                .buildClient();

        // Initialize conversation history with system message
        this.chatMessages = new ArrayList<>();
        chatMessages.add(new ChatRequestSystemMessage("You are a helpful assistant."));

        // Initialize PDF document
        PdfWriter writer = new PdfWriter("chat_responses.pdf");
        PdfDocument pdfDoc = new PdfDocument(writer);
        this.pdfDocument = new Document(pdfDoc);
    }

    /**
     * Process user input, get a response from the assistant, and save to PDF.
     *
     * @param userInput the input from the user
     * @return the assistant's response
     */
    public String processUserInput(String userInput) {
        if (userInput == null || userInput.trim().isEmpty()) {
            return "Please enter a valid message.";
        }

        // Detect resume generation request
        boolean isResumeRequest = userInput.toLowerCase().contains("generate a professional resume");

        // Add user message to conversation history
        ChatRequestUserMessage userMessage = new ChatRequestUserMessage(userInput);
        chatMessages.add(userMessage);

        // Set up chat options with the entire conversation history
        ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(chatMessages);
        chatCompletionsOptions.setModel("gpt-4o-mini");

        // Get response from Azure's Chat API
        ChatCompletions completions = client.complete(chatCompletionsOptions);
        String assistantResponse = "";

        // Process response and update the conversation history
        for (var choice : completions.getChoices()) {
            assistantResponse = choice.getMessage().getContent();
            System.out.printf("Assistant: %s%n", assistantResponse);

            // Format the output differently if it's a resume request
            if (isResumeRequest) {
                pdfDocument.add(new Paragraph("Generated Resume:"));
                pdfDocument.add(new Paragraph("Name: " + userInput));
                pdfDocument.add(new Paragraph("Resume Details:\n" + assistantResponse));
            } else {
                pdfDocument.add(new Paragraph("You: " + userInput));
                pdfDocument.add(new Paragraph("Assistant: " + assistantResponse));
            }

            // Add space between interactions
            pdfDocument.add(new Paragraph(""));

            // Convert the assistant response to a ChatRequestAssistantMessage and add to conversation history
            ChatRequestAssistantMessage assistantMessage = new ChatRequestAssistantMessage(assistantResponse);
            chatMessages.add(assistantMessage);
        }

        return assistantResponse;
    }

    /**
     * Closes the PDF document. Should be called when all interactions are complete.
     */
    public void close() {
        pdfDocument.close();
        System.out.println("PDF file 'chat_responses.pdf' created with chat history.");
    }
}
