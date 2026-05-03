package com.genesis.backend.domain.model

enum class SectionId {
    VISION, REQUIREMENTS, TECH_STACK, DATA_MODEL, ENDPOINTS, UX_FLOW, COMPONENTS
}

enum class SectionStatus {
    EMPTY, DRAFT, COMPLETE
}

enum class MessageRole {
    USER, MODEL, SYSTEM
}

data class SpecSection(
    val id: SectionId,
    val title: String,
    val content: String,
    val status: SectionStatus
)

data class SDD(
    val projectName: String,
    val sections: Map<SectionId, SpecSection>,
    val lastUpdated: Long
)

data class Message(
    val role: MessageRole,
    val content: String
)

data class AiConfig(
    val providerId: String,
    val modelName: String,
    val systemInstruction: String
)

data class AiResponse(
    val textContent: String?,
    val toolCalls: List<ToolCall> = emptyList()
)

sealed interface ToolCall {
    val id: String // Optional identifier for the tool call execution
    
    data class UpdateSpecSection(
        override val id: String,
        val sectionId: String, 
        val content: String,
        val status: String
    ) : ToolCall
    
    data class UpdateProjectName(
        override val id: String,
        val name: String
    ) : ToolCall
}
