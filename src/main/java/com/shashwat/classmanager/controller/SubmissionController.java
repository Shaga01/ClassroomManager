package com.shashwat.classmanager.controller;

import com.shashwat.classmanager.model.Student;
import com.shashwat.classmanager.model.Submission;
import com.shashwat.classmanager.service.SubmissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    // POST /submissions?assignmentId=1&studentId=1
    @PostMapping
    public Submission submitAssignment(@RequestParam Long assignmentId,
                                       @RequestParam Long studentId) {
        return submissionService.submitAssignment(assignmentId, studentId);
    }

    // GET /submissions/assignment/1/submitted — who submitted?
    @GetMapping("/assignment/{assignmentId}/submitted")
    public List<Student> getStudentsWhoSubmitted(@PathVariable Long assignmentId) {
        return submissionService.getStudentsWhoSubmitted(assignmentId);
    }

    // GET /submissions/assignment/1/missing — who didn't submit?
    @GetMapping("/assignment/{assignmentId}/missing")
    public List<Student> getStudentsWhoDidNotSubmit(@PathVariable Long assignmentId) {
        return submissionService.getStudentsWhoDidNotSubmit(assignmentId);
    }
}