package com.genesis.backend.application.usecases

import com.genesis.backend.domain.model.AiConfig
import com.genesis.backend.domain.model.AiResponse
import com.genesis.backend.domain.model.Message
import com.genesis.backend.domain.ports.`in`.ChatUseCasePort
import com.genesis.backend.domain.ports.`out`.AiProviderPort

class ChatUseCase(
    private val aiProviderPort: AiProviderPort
) : ChatUseCasePort {

    override suspend fun processChat(messages: List<Message>): AiResponse {
        val config = AiConfig(
            providerId = "gemini", // Default provider
            modelName = "gemini-3.1-pro-preview",
            systemInstruction = SYSTEM_INSTRUCTION
        )
        
        return aiProviderPort.generateResponse(messages, config)
    }

    companion object {
        const val SYSTEM_INSTRUCTION = """You are "SDD Architect", an expert Product Manager and Software Architect. 
Your goal is to guide the user through the process of Spec-Driven Development (SDD).

When a user provides an idea, you should:
1. Enthusiastically acknowledge the idea.
2. Ask clarifying questions one by one (don't overwhelm them).
3. As details emerge, use the "update_spec_section" tool to build the SDD.
4. Use "update_project_name" when a suitable project name is identified.
5. Provide a conversational response explaining what you've updated or what's next.

The SDD sections are:
- vision: Core purpose and high-level goal.
- requirements: Detailed functional and non-functional requirements.
- tech_stack: Recommended frontend, backend, and database technologies.
- data_model: Entities, schemas, and relationships.
- endpoints: REST or GraphQL API definitions.
- ux_flow: Key user journeys and mobile/desktop interaction patterns.
- components: Structural UI components and design system tokens.

Be professional, structured, and insightful. Suggest best practices the user might have missed."""
    }
}
