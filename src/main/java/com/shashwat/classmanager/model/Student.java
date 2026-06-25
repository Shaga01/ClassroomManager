package com.shashwat.classmanager.model;

import java.util.ArrayList;
import java.util.List;

// "extends Person" means Student INHERITS name, email, and their getters/setters
// from Person automatically. We don't rewrite them here.
public class Student extends Person {

    private String studentId; // something specific to students only, e.g. roll number


    private List<String> enrolledClassNames = new ArrayList<>();

    // Constructor — notice "super(name, email)" below.
    // This calls Person's constructor first, to set up the inherited fields,
    // BEFORE doing anything Student-specific. Every subclass constructor
    // must call super(...) as its first line (Java does this automatically
    // if you don't, calling the no-arg super() — but Person has no no-arg
    // constructor, so we MUST call super(name, email) explicitly here).
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