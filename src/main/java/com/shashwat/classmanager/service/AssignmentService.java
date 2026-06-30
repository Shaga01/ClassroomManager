package com.shashwat.classmanager.service;

import com.shashwat.classmanager.model.Assignment;
import com.shashwat.classmanager.model.ClassRoom;
import com.shashwat.classmanager.repository.AssignmentRepository;
import com.shashwat.classmanager.repository.ClassRoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final ClassRoomRepository classRoomRepository;

    public AssignmentService(AssignmentRepository assignmentRepository,
                             ClassRoomRepository classRoomRepository) {
        this.assignmentRepository = assignmentRepository;
        this.classRoomRepository = classRoomRepository;
    }

    public Assignment createAssignment(String title, LocalDate dueDate, Long classRoomId) {
        ClassRoom classRoom = classRoomRepository.findById(classRoomId)
                .orElseThrow(() -> new RuntimeException("ClassRoom not found with id: " + classRoomId));

        Assignment assignment = new Assignment(title, dueDate, classRoom);
        return assignmentRepository.save(assignment);
    }

    public List<Assignment> getAssignmentsForClassRoom(Long classRoomId) {
        return assignmentRepository.findByClassRoomId(classRoomId);
    }
}