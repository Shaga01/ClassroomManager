package com.shashwat.classmanager;

import com.shashwat.classmanager.model.Faculty;
import com.shashwat.classmanager.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClassmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassmanagerApplication.class, args);

		Student s = new Student("Alice", "alice@email.com", "S101");
		Faculty f = new Faculty("Dr. Smith", "smith@email.com", "Computer Science");

		System.out.println("Student: " + s.getName() + ", ID: " + s.getStudentId());
		System.out.println("Faculty: " + f.getName() + ", Dept: " + f.getDepartment());
	}

}
