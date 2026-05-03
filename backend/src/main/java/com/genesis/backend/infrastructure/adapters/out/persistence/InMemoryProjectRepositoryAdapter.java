package com.genesis.backend.infrastructure.adapters.out.persistence;

import com.genesis.backend.domain.model.SDD;
import com.genesis.backend.domain.ports.out.ProjectRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryProjectRepositoryAdapter implements ProjectRepositoryPort {

    private final Map<String, SDD> projects = new ConcurrentHashMap<>();

    @Override
    public List<String> listProjects() {
        return new ArrayList<>(projects.keySet());
    }

    @Override
    public SDD getProject(String projectName) {
        return projects.get(projectName);
    }

    @Override
    public void saveProject(SDD project) {
        if (project != null && project.projectName() != null) {
            projects.put(project.projectName(), project);
        }
    }
}
