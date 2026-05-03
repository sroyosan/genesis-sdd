package com.genesis.backend.infrastructure.adapters.in.web;

public record SpecSectionDto(
    String id,
    String title,
    String content,
    String status
) {}
