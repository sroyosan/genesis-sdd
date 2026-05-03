package com.genesis.backend.infrastructure.adapters.`in`.web.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import com.genesis.backend.domain.ports.`in`.ChatUseCasePort
import com.genesis.backend.domain.ports.`out`.ProjectRepositoryPort
import com.genesis.backend.infrastructure.adapters.`in`.web.chatRoutes
import com.genesis.backend.infrastructure.adapters.`in`.web.projectRoutes

fun Application.configureRouting(chatUseCase: ChatUseCasePort, projectRepository: ProjectRepositoryPort) {
    routing {
        get("/") {
            call.respondText("Genesis SDD Backend API")
        }
        
        chatRoutes(chatUseCase)
        projectRoutes(projectRepository)
    }
}
