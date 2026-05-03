package com.genesis.backend.domain.ports.out;

import com.genesis.backend.domain.model.SDD;

import java.util.List;

public interface ProjectRepositoryPort {
    List<String> listProjects();
    SDD getProject(String projectName);
    void saveProject(SDD project);
}
