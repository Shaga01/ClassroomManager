package com.shashwat.classmanager.repository;

import com.shashwat.classmanager.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    // A custom query method — Spring Data JPA auto-generates the SQL for this
    // just from the method name. It parses "findByClassRoomId" and knows to
    // generate: SELECT * FROM assignment WHERE classroom_id = ?
    List<Assignment> findByClassRoomId(Long classRoomId);
}