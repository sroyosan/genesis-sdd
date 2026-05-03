package com.genesis.backend

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.github.cdimascio.dotenv.dotenv
import com.genesis.backend.infrastructure.adapters.`in`.web.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val dotenv = dotenv {
        ignoreIfMissing = true
    }
    
    val apiKey = dotenv["GEMINI_API_KEY"] ?: System.getenv("GEMINI_API_KEY") ?: ""
    val aiProviderFactory = com.genesis.backend.infrastructure.adapters.`out`.ai.AiProviderFactory(apiKey)
    val chatUseCase = com.genesis.backend.application.usecases.ChatUseCase(aiProviderFactory.getProvider("gemini"))
    val projectRepository = com.genesis.backend.infrastructure.adapters.`out`.db.ExposedProjectRepository()

    configureSerialization()
    configureHTTP()
    configureRouting(chatUseCase, projectRepository)
}
