package com.furniturewala.backend.repository;

import com.furniturewala.backend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByProjectIdOrderByIdAsc(Long projectId);
    long countByProjectId(Long projectId);
    void deleteByProjectId(Long projectId);
}
