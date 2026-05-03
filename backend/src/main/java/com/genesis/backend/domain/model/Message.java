package com.genesis.backend.domain.model;

public record Message(
    MessageRole role,
    String content
) {}
