package com.shashwat.classmanager.repository;

import com.shashwat.classmanager.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}