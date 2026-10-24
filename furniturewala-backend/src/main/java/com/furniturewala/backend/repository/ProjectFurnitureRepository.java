package com.furniturewala.backend.repository;

import com.furniturewala.backend.model.ProjectFurniture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectFurnitureRepository extends JpaRepository<ProjectFurniture, Long> {
    List<ProjectFurniture> findByRoomIdOrderByIdAsc(Long roomId);
    List<ProjectFurniture> findByRoomProjectIdOrderByRoomIdAscIdAsc(Long projectId);
    long countByRoomId(Long roomId);
    void deleteByRoomId(Long roomId);
}
