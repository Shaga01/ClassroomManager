package com.shashwat.classmanager.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String className; // e.g. "Math 101"

    // MANY ClassRooms can belong to ONE Faculty.
    // This is the "many" side of a one-to-many relationship.
    @ManyToOne
    // @JoinColumn tells JPA exactly which column in the classroom table
    // stores the foreign key pointing back to the faculty table.
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    // MANY Students can be enrolled in MANY ClassRooms (a student takes
    // multiple classes, a class has multiple students) — that's @ManyToMany.
    @ManyToMany
    // @JoinTable creates a separate "linking" table in the database,
    // since a many-to-many relationship can't be stored with a simple
    // foreign key on either side — you need a middle table with two
    // foreign keys (one to classroom, one to student).
    @JoinTable(
            name = "classroom_students",
            joinColumns = @JoinColumn(name = "classroom_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students = new ArrayList<>();

    protected ClassRoom() {
    }

    public ClassRoom(String className, Faculty faculty) {
        this.className = className;
        this.faculty = faculty;
    }

    public Long getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public List<Student> getStudents() {
        return students;
    }

    // A small business method — adding a student to this class
    public void addStudent(Student student) {
        students.add(student);
    }
}