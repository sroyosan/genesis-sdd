package com.genesis.backend.domain.model;

public record SpecSection(
    SectionId id,
    String title,
    String content,
    SectionStatus status
) {}
