package com.shashwat.classmanager.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private LocalDate submittedOn;

    protected Submission() {
    }

    public Submission(Assignment assignment, Student student, LocalDate submittedOn) {
        this.assignment = assignment;
        this.student = student;
        this.submittedOn = submittedOn;
    }

    public Long getId() {
        return id;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public Student getStudent() {
        return student;
    }

    public LocalDate getSubmittedOn() {
        return submittedOn;
    }

    // A small business method, useful for the "who's late" feature we'll build soon
    public boolean isLate() {
        return submittedOn.isAfter(assignment.getDueDate());
    }
}