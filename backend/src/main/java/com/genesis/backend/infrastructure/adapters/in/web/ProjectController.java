package com.genesis.backend.infrastructure.adapters.in.web;

import com.genesis.backend.domain.model.SDD;
import com.genesis.backend.domain.model.SectionId;
import com.genesis.backend.domain.model.SectionStatus;
import com.genesis.backend.domain.model.SpecSection;
import com.genesis.backend.domain.ports.out.ProjectRepositoryPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
public class ProjectController {

    private final ProjectRepositoryPort projectRepository;

    public ProjectController(ProjectRepositoryPort projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public List<String> listProjects() {
        return projectRepository.listProjects();
    }

    @GetMapping("/{projectName}")
    public ResponseEntity<SddDto> getProject(@PathVariable String projectName) {
        SDD project = projectRepository.getProject(projectName);
        if (project == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, SpecSectionDto> sectionsDto = project.sections().entrySet().stream()
            .collect(Collectors.toMap(
                e -> e.getKey().name().toLowerCase(),
                e -> new SpecSectionDto(
                    e.getValue().id().name().toLowerCase(),
                    e.getValue().title(),
                    e.getValue().content(),
                    e.getValue().status().name().toLowerCase()
                )
            ));

        SddDto dto = new SddDto(project.projectName(), sectionsDto, project.lastUpdated());
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> createOrUpdateProject(@RequestBody SddDto dto) {
        Map<SectionId, SpecSection> sections = dto.sections().entrySet().stream()
            .collect(Collectors.toMap(
                e -> SectionId.valueOf(e.getKey().toUpperCase()),
                e -> new SpecSection(
                    SectionId.valueOf(e.getValue().id().toUpperCase()),
                    e.getValue().title(),
                    e.getValue().content(),
                    SectionStatus.valueOf(e.getValue().status().toUpperCase())
                )
            ));

        SDD sdd = new SDD(dto.projectName(), sections, dto.lastUpdated());
        projectRepository.saveProject(sdd);
        
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
