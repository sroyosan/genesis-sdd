package com.genesis.backend.domain.model;

public sealed interface ToolCall permits ToolCall.UpdateSpecSection, ToolCall.UpdateProjectName {
    
    String id();

    record UpdateSpecSection(
        String id,
        String sectionId,
        String content,
        String status
    ) implements ToolCall {}

    record UpdateProjectName(
        String id,
        String name
    ) implements ToolCall {}
}
