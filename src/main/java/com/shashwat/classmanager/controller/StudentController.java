package com.shashwat.classmanager.controller;

import com.shashwat.classmanager.model.Student;
import com.shashwat.classmanager.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController = @Controller + @ResponseBody combined.
// It tells Spring: "this class handles incoming HTTP requests, and whatever
// the methods return should be converted directly to JSON in the response
// (not rendered as an HTML page)."
@RestController

// @RequestMapping sets a base URL path for EVERY method in this class.
// So every endpoint below will start with /students
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    // Same pattern as before — constructor injection. Spring hands us
    // the StudentService bean automatically.
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Handles: GET http://localhost:8080/students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Handles: POST http://localhost:8080/students
    // @RequestBody means: take the JSON sent in the request, and convert it
    // into a Student object automatically (Spring uses a library called
    // Jackson behind the scenes to do this JSON <-> Java conversion).
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }
}