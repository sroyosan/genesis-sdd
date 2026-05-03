package com.genesis.backend.infrastructure.adapters.out.ollama.dto;

import java.util.List;
import java.util.Map;

public record OllamaResponse(
    String model,
    OllamaResponseMessage message,
    boolean done
) {
    public record OllamaResponseMessage(
        String role,
        String content,
        List<OllamaToolCall> tool_calls
    ) {}

    public record OllamaToolCall(
        OllamaToolFunction function
    ) {}

    public record OllamaToolFunction(
        String name,
        Map<String, Object> arguments
    ) {}
}
