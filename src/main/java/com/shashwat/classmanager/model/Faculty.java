package com.shashwat.classmanager.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Faculty extends Person {

    private String department;

    @ElementCollection
    private List<String> classNamesTaught = new ArrayList<>();

    // ONE Faculty can have MANY ClassRooms.
    // "mappedBy = faculty" means: "don't create a new foreign key column here.
    // The relationship is already defined on the OTHER side (ClassRoom.faculty
    // field) — just look there to figure out which classrooms belong to me."
    @OneToMany(mappedBy = "faculty")
    private List<ClassRoom> classRooms = new ArrayList<>();

    protected Faculty() {
    }

    public Faculty(String name, String email, String department) {
        super(name, email);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<String> getClassNamesTaught() {
        return classNamesTaught;
    }

    public List<ClassRoom> getClassRooms() {
        return classRooms;
    }
}