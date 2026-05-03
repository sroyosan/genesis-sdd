package com.genesis.backend.domain.model;

public record AiConfig(
    String providerId,
    String modelName,
    String systemInstruction
) {}
