package com.genesis.backend.infrastructure.adapters.in.web;

import java.util.Map;

public record SddDto(
    String projectName,
    Map<String, SpecSectionDto> sections,
    long lastUpdated
) {}
