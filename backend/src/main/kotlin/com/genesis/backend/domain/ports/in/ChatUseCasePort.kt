package com.genesis.backend.domain.ports.`in`

import com.genesis.backend.domain.model.Message
import com.genesis.backend.domain.model.AiResponse

interface ChatUseCasePort {
    suspend fun processChat(messages: List<Message>): AiResponse
}
