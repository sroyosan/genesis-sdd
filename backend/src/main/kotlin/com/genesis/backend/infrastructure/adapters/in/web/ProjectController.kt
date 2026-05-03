package com.genesis.backend.infrastructure.adapters.`in`.web

import com.genesis.backend.domain.model.SDD
import com.genesis.backend.domain.model.SpecSection
import com.genesis.backend.domain.model.SectionId
import com.genesis.backend.domain.model.SectionStatus
import com.genesis.backend.domain.ports.out.ProjectRepositoryPort
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
data class SddDto(
    val projectName: String,
    val sections: Map<String, SpecSectionDto>,
    val lastUpdated: Long
)

@Serializable
data class SpecSectionDto(
    val id: String,
    val title: String,
    val content: String,
    val status: String
)

fun Route.projectRoutes(projectRepository: ProjectRepositoryPort) {
    route("/api/projects") {
        get {
            call.respond(projectRepository.listProjects())
        }

        get("/{projectName}") {
            val name = call.parameters["projectName"] ?: return@get call.respond(HttpStatusCode.BadRequest, "Missing name")
            val project = projectRepository.getProject(name)
            if (project != null) {
                val dto = SddDto(
                    projectName = project.projectName,
                    sections = project.sections.mapKeys { it.key.name.lowercase() }.mapValues { 
                        SpecSectionDto(
                            id = it.value.id.name.lowercase(),
                            title = it.value.title,
                            content = it.value.content,
                            status = it.value.status.name.lowercase()
                        ) 
                    },
                    lastUpdated = project.lastUpdated
                )
                call.respond(dto)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        post {
            val dto = call.receive<SddDto>()
            val sdd = SDD(
                projectName = dto.projectName,
                sections = dto.sections.mapKeys { SectionId.valueOf(it.key.uppercase()) }.mapValues {
                    SpecSection(
                        id = SectionId.valueOf(it.value.id.uppercase()),
                        title = it.value.title,
                        content = it.value.content,
                        status = SectionStatus.valueOf(it.value.status.uppercase())
                    )
                },
                lastUpdated = dto.lastUpdated
            )
            projectRepository.saveProject(sdd)
            call.respond(HttpStatusCode.Created)
        }
    }
}
