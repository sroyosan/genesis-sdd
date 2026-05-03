package com.genesis.backend.infrastructure.adapters.in.web;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ToolCallDto(
    String type,
    String id,
    String sectionId,
    String content,
    String status,
    String name
) {}
