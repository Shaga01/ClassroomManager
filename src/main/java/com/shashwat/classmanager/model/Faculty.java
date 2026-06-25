package com.shashwat.classmanager.model;

import java.util.ArrayList;
import java.util.List;

public class Faculty extends Person {

    private String department; // specific to Faculty only

    // A faculty can own/teach multiple classes
    private List<String> classNamesTaught = new ArrayList<>();

    public Faculty(String name, String email, String department) {
        super(name, email); // same pattern as Student — must call Person's constructor first
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