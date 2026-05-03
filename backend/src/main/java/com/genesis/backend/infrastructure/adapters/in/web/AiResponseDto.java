package com.genesis.backend.infrastructure.adapters.in.web;

import java.util.List;

public record AiResponseDto(
    String textContent,
    List<ToolCallDto> toolCalls
) {}
