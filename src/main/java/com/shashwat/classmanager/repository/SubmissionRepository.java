package com.shashwat.classmanager.repository;

import com.shashwat.classmanager.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    // Same pattern — find all submissions for a given assignment
    List<Submission> findByAssignmentId(Long assignmentId);
}