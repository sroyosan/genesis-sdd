package com.genesis.backend.domain.model;

import java.util.Map;

public record SDD(
    String projectName,
    Map<SectionId, SpecSection> sections,
    long lastUpdated
) {}
