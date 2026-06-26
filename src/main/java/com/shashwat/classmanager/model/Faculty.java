package com.shashwat.classmanager.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Faculty extends Person {

    private String department;

    @ElementCollection
    private List<String> classNamesTaught = new ArrayList<>();

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
}