package com.shashwat.classmanager.service;

import com.shashwat.classmanager.model.Student;
import com.shashwat.classmanager.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service tells Spring: "create one object of this class automatically,
// and manage it for me." This object is now called a BEAN — any object
// that Spring creates and manages (instead of you writing `new StudentService()`
// yourself) is called a bean.
@Service
public class StudentService {

    // This service NEEDS a StudentRepository to do its job (to actually
    // talk to the database). Instead of creating one itself like:
    //     private StudentRepository repo = new StudentRepository();
    // (which wouldn't even work — remember, it's an interface, you can't "new" it)
    // we DECLARE that we need one, and let Spring hand it to us.
    private final StudentRepository studentRepository;

    // CONSTRUCTOR INJECTION — this is dependency injection in practice.
    // When Spring creates a StudentService bean, it sees this constructor
    // needs a StudentRepository. Spring already has a StudentRepository bean
    // ready (remember, it auto-generated one from our interface). So Spring
    // automatically passes it in here. We never call "new" ourselves.
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Business logic method: save a new student
    public Student addStudent(Student student) {
        return studentRepository.save(student); // delegates the actual DB work to the repo
    }

    // Business logic method: get every student
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}