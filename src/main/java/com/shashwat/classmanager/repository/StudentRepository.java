package com.shashwat.classmanager.repository;

import com.shashwat.classmanager.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

// Notice: this is an INTERFACE, not a class. We don't write any method bodies.
// JpaRepository<Student, Long> means:
//   - Student  → the entity this repository manages
//   - Long     → the type of Student's @Id field
public interface StudentRepository extends JpaRepository<Student, Long> {
}