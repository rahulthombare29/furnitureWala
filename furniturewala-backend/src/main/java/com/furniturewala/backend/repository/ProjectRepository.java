package com.furniturewala.backend.repository;

import com.furniturewala.backend.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByCustomerIdOrderByCreatedAtDesc(Long customerId);
    List<Project> findByStatusOrderByCreatedAtDesc(Project.Status status);
    List<Project> findByCustomerIdAndStatus(Long customerId, Project.Status status);
}
