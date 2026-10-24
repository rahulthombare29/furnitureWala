package com.furniturewala.backend.service.impl;

import com.furniturewala.backend.dto.*;
import com.furniturewala.backend.exception.*;
import com.furniturewala.backend.model.*;
import com.furniturewala.backend.repository.*;
import com.furniturewala.backend.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository repo;
    private final CustomerRepository customers;

    public ProjectServiceImpl(ProjectRepository repo, CustomerRepository customers) {
        this.repo = repo;
        this.customers = customers;
    }

    private ProjectResponse map(Project x) {
        return new ProjectResponse(x.getId(), x.getCustomer().getId(), x.getProjectName(), x.getHomeType(), x.getCarpetAreaSqft(), x.getCity(), x.getAddress(), x.getStatus(), x.getCreatedAt());
    }

    public List<ProjectResponse> findAll() {
        return repo.findAll().stream().map(this::map).toList();
    }

    @Transactional(readOnly = true)
    public ProjectResponse findById(Long id) {
        return map(repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found: " + id)));
    }

    public ProjectResponse create(ProjectRequest r) {
        Project x = new Project();
        apply(x, r);
        return map(repo.save(x));
    }

    public ProjectResponse update(Long id, ProjectRequest r) {
        Project x = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found: " + id));
        apply(x, r);
        return map(repo.save(x));
    }

    private void apply(Project x, ProjectRequest r) {
        Customer c = customers.findById(r.customerId()).orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + r.customerId()));
        x.setCustomer(c);
        x.setProjectName(r.projectName().trim());
        x.setHomeType(r.homeType());
        x.setCarpetAreaSqft(r.carpetAreaSqft());
        x.setCity(r.city());
        x.setAddress(r.address());
        if (r.status() != null) x.setStatus(r.status());
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Project not found: " + id);
        repo.deleteById(id);
    }
}
