package com.RuanPablo2.BeanCounter.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.RuanPablo2.BeanCounter.dto.request.TransactionRequestDTO;
import com.RuanPablo2.BeanCounter.dto.response.TransactionResponseDTO;
import com.RuanPablo2.BeanCounter.entity.enums.TransactionType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Service
public class SmartTransactionService {

    private final TransactionService transactionService;
    private final ObjectMapper objectMapper;
    private final RestClient restClient;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public SmartTransactionService(TransactionService transactionService, ObjectMapper objectMapper) {
        this.transactionService = transactionService;
        this.objectMapper = objectMapper;
        this.restClient = RestClient.create();
    }

    public record AiParsedData(String description, BigDecimal amount, TransactionType type, String category, String friendlyMessage) {}
    public record SmartTransactionResponse(TransactionResponseDTO transaction, String message) {}

    public SmartTransactionResponse processFreeText(String rawText, Long userId) {
        String prompt = """
            You are a smart financial assistant. Parse the following user input and extract the transaction details.
            Return ONLY a valid JSON, without markdown blocks. Exact keys required:
            - "description": (String) Clean, format, and capitalize the name of the establishment/income (e.g., "McDonald's", "Uber").
            - "amount": (Number) The absolute monetary value.
            - "type": (String) strictly "EXPENSE" or "INCOME".
            - "category": (String) A short category name in Portuguese (e.g., "Alimentação", "Transporte", "Lazer").
            - "friendlyMessage": (String) A short, friendly confirmation message IN PORTUGUESE (e.g., "Gasto de R$ 50,67 no Uber registrado com sucesso!").
            
            User input: "%s"
            """.formatted(rawText);

        var payload = Map.of("contents", new Object[]{
                Map.of("parts", new Object[]{
                        Map.of("text", prompt)
                })
        });

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3-flash-preview:generateContent?key=" + geminiApiKey;

        try {
            String responseBody = restClient.post()
                    .uri(url)
                    .body(payload)
                    .retrieve()
                    .body(String.class);

            JsonNode rootNode = objectMapper.readTree(responseBody);
            String aiText = rootNode.path("candidates").get(0)
                    .path("content").path("parts").get(0)
                    .path("text").asText();

            String cleanJson = aiText.replace("```json", "").replace("```", "").trim();

            AiParsedData parsedData = objectMapper.readValue(cleanJson, AiParsedData.class);

            TransactionRequestDTO requestDTO = new TransactionRequestDTO();
            requestDTO.setDescription(parsedData.description());
            requestDTO.setAmount(parsedData.amount());
            requestDTO.setType(parsedData.type());
            requestDTO.setCategory(parsedData.category());
            requestDTO.setDate(LocalDate.now());

            TransactionResponseDTO savedTransaction = transactionService.create(requestDTO, userId);

            return new SmartTransactionResponse(savedTransaction, parsedData.friendlyMessage());

        } catch (Exception e) {
            throw new RuntimeException("Failed to process smart transaction via AI", e);
        }
    }
}