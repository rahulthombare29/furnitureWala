package com.furniturewala.backend.service;

import com.furniturewala.backend.dto.*;

import java.util.*;

public interface ProjectService {
    List<ProjectResponse> findAll();

    ProjectResponse findById(Long id);

    ProjectResponse create(ProjectRequest r);

    ProjectResponse update(Long id, ProjectRequest r);

    void delete(Long id);
}
