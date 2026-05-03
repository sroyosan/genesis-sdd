package com.genesis.backend.infrastructure.adapters.`in`.web

import com.genesis.backend.domain.model.Message
import com.genesis.backend.domain.ports.`in`.ChatUseCasePort
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
data class ChatRequest(val messages: List<MessageDto>)

@Serializable
data class MessageDto(val role: String, val content: String)

@Serializable
data class AiResponseDto(
    val textContent: String?,
    val toolCalls: List<ToolCallDto>
)

@Serializable
data class ToolCallDto(
    val type: String,
    val id: String,
    val sectionId: String? = null,
    val content: String? = null,
    val status: String? = null,
    val name: String? = null
)

fun Route.chatRoutes(chatUseCase: ChatUseCasePort) {
    post("/api/chat") {
        val request = call.receive<ChatRequest>()
        
        val domainMessages = request.messages.map {
            val role = when (it.role.lowercase()) {
                "user" -> com.genesis.backend.domain.model.MessageRole.USER
                "model" -> com.genesis.backend.domain.model.MessageRole.MODEL
                "system" -> com.genesis.backend.domain.model.MessageRole.SYSTEM
                else -> com.genesis.backend.domain.model.MessageRole.USER
            }
            Message(role = role, content = it.content)
        }

        val aiResponse = chatUseCase.processChat(domainMessages)
        
        val responseDto = AiResponseDto(
            textContent = aiResponse.textContent,
            toolCalls = aiResponse.toolCalls.map { toolCall ->
                when (toolCall) {
                    is com.genesis.backend.domain.model.ToolCall.UpdateSpecSection -> {
                        ToolCallDto(
                            type = "update_spec_section",
                            id = toolCall.id,
                            sectionId = toolCall.sectionId,
                            content = toolCall.content,
                            status = toolCall.status
                        )
                    }
                    is com.genesis.backend.domain.model.ToolCall.UpdateProjectName -> {
                        ToolCallDto(
                            type = "update_project_name",
                            id = toolCall.id,
                            name = toolCall.name
                        )
                    }
                }
            }
        )
        
        call.respond(responseDto)
    }
}
