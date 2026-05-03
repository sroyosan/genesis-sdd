package com.genesis.backend.domain.model;

import java.util.List;

public record AiResponse(
    String textContent,
    List<ToolCall> toolCalls
) {
    public AiResponse(String textContent) {
        this(textContent, List.of());
    }
}
