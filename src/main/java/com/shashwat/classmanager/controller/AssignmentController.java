package com.shashwat.classmanager.controller;

import com.shashwat.classmanager.model.Assignment;
import com.shashwat.classmanager.service.AssignmentService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    // POST /assignments?title=HW1&dueDate=2026-07-10&classRoomId=1
    @PostMapping
    public Assignment createAssignment(@RequestParam String title,
                                       @RequestParam LocalDate dueDate,
                                       @RequestParam Long classRoomId) {
        return assignmentService.createAssignment(title, dueDate, classRoomId);
    }

    // GET /assignments/classroom/1 — get all assignments for a classroom
    @GetMapping("/classroom/{classRoomId}")
    public List<Assignment> getAssignmentsForClassRoom(@PathVariable Long classRoomId) {
        return assignmentService.getAssignmentsForClassRoom(classRoomId);
    }
}