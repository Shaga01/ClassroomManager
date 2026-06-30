package com.shashwat.classmanager.service;

import com.shashwat.classmanager.model.ClassRoom;
import com.shashwat.classmanager.model.Faculty;
import com.shashwat.classmanager.model.Student;
import com.shashwat.classmanager.repository.ClassRoomRepository;
import com.shashwat.classmanager.repository.FacultyRepository;
import com.shashwat.classmanager.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassRoomService {

    // Notice: this service needs THREE repositories. Constructor injection
    // scales naturally — just add more parameters, Spring wires all of them.
    private final ClassRoomRepository classRoomRepository;
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public ClassRoomService(ClassRoomRepository classRoomRepository,
                            FacultyRepository facultyRepository,
                            StudentRepository studentRepository) {
        this.classRoomRepository = classRoomRepository;
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    // Create a new classroom, owned by a given faculty (by their id)
    public ClassRoom createClassRoom(String className, Long facultyId) {
        // findById returns an Optional<Faculty> — JPA's way of saying
        // "this might or might not exist, you must handle both cases."
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + facultyId));

        ClassRoom classRoom = new ClassRoom(className, faculty);
        return classRoomRepository.save(classRoom);
    }

    // Enroll a student into a classroom
    public ClassRoom enrollStudent(Long classRoomId, Long studentId) {
        ClassRoom classRoom = classRoomRepository.findById(classRoomId)
                .orElseThrow(() -> new RuntimeException("ClassRoom not found with id: " + classRoomId));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        classRoom.addStudent(student); // the helper method we wrote earlier
        return classRoomRepository.save(classRoom);
    }

    public List<ClassRoom> getAllClassRooms() {
        return classRoomRepository.findAll();
    }
}