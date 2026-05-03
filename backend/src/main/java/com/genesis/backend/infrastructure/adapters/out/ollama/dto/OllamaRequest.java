package com.genesis.backend.infrastructure.adapters.out.ollama.dto;

import java.util.List;
import java.util.Map;

public record OllamaRequest(
    String model,
    List<OllamaMessage> messages,
    List<OllamaTool> tools,
    boolean stream
) {
    public record OllamaMessage(String role, String content) {}

    public record OllamaTool(
        String type,
        OllamaFunction function
    ) {}

    public record OllamaFunction(
        String name,
        String description,
        OllamaParameters parameters
    ) {}

    public record OllamaParameters(
        String type,
        Map<String, OllamaProperty> properties,
        List<String> required
    ) {}

    public record OllamaProperty(
        String type,
        String description
    ) {}
}
