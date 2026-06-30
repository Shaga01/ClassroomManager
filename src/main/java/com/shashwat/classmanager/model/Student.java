package com.shashwat.classmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // Student gets its OWN table, joined back to Person's table via shared id
public class Student extends Person {

    private String studentId;

    // We're skipping the real @ManyToMany relationship to ClassRoom for now —
    // we'll add that properly once ClassRoom exists as an entity.
    // For now, JPA needs a way to store a List<String> in a table, so we use
    // @ElementCollection — tells JPA "this isn't a relationship to another
    // entity, it's just a simple list of values, store it in its own small table."
    @ElementCollection
    private List<String> enrolledClassNames = new ArrayList<>();

    // The OTHER side of the @ManyToMany relationship defined in ClassRoom.
    // mappedBy = "students" matches the field name in ClassRoom.
    // We do NOT repeat @JoinTable here — that's only defined once,
    // on whichever side "owns" the relationship (we chose ClassRoom).
    @ManyToMany(mappedBy = "students")
    @JsonIgnore
    private List<ClassRoom> classRooms = new ArrayList<>();

    // No-arg constructor required by JPA, same reason as Person
    protected Student() {
    }

    public Student(String name, String email, String studentId) {
        super(name, email);
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public List<String> getEnrolledClassNames() {
        return enrolledClassNames;
    }
}