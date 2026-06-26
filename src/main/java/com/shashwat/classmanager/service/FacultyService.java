package com.shashwat.classmanager.service;

import com.shashwat.classmanager.model.Faculty;
import com.shashwat.classmanager.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }
}