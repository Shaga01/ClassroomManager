package com.shashwat.classmanager.service;

import com.shashwat.classmanager.model.Assignment;
import com.shashwat.classmanager.model.Student;
import com.shashwat.classmanager.model.Submission;
import com.shashwat.classmanager.repository.AssignmentRepository;
import com.shashwat.classmanager.repository.StudentRepository;
import com.shashwat.classmanager.repository.SubmissionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final AssignmentRepository assignmentRepository;
    private final StudentRepository studentRepository;

    public SubmissionService(SubmissionRepository submissionRepository,
                             AssignmentRepository assignmentRepository,
                             StudentRepository studentRepository) {
        this.submissionRepository = submissionRepository;
        this.assignmentRepository = assignmentRepository;
        this.studentRepository = studentRepository;
    }

    public Submission submitAssignment(Long assignmentId, Long studentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found with id: " + assignmentId));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        Submission submission = new Submission(assignment, student, LocalDate.now());
        return submissionRepository.save(submission);
    }

    // Returns students who submitted a given assignment
    public List<Student> getStudentsWhoSubmitted(Long assignmentId) {
        return submissionRepository.findByAssignmentId(assignmentId)
                .stream() // converts the List into a Stream, so we can transform it
                .map(Submission::getStudent) // for each Submission, extract just the Student
                .collect(Collectors.toList()); // gather results back into a List
    }

    // Returns students enrolled in the classroom who did NOT submit
    public List<Student> getStudentsWhoDidNotSubmit(Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found with id: " + assignmentId));

        List<Student> allEnrolledStudents = assignment.getClassRoom().getStudents();
        List<Student> submittedStudents = getStudentsWhoSubmitted(assignmentId);

        return allEnrolledStudents.stream()
                .filter(student -> !submittedStudents.contains(student)) // keep only students NOT in the submitted list
                .collect(Collectors.toList());
    }
}