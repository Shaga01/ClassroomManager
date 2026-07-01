package com.shashwat.classmanager;

import com.shashwat.classmanager.model.Student;
import com.shashwat.classmanager.repository.StudentRepository;
import com.shashwat.classmanager.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Tells JUnit to use Mockito's extension — enables @Mock and @InjectMocks
@ExtendWith(MockitoExtension.class)
public class ClassmanagerApplicationTests {

	// @Mock creates a FAKE StudentRepository.
	// We control exactly what it returns — no real database involved.
	@Mock
	private StudentRepository studentRepository;

	// @InjectMocks creates a REAL StudentService, but automatically
	// injects the @Mock StudentRepository into it via the constructor.
	// This is exactly why constructor injection is the best practice —
	// Mockito can inject mocks cleanly this way.
	@InjectMocks
	private StudentService studentService;

	// Test data — reusable across tests
	private Student testStudent;

	// @BeforeEach runs before EVERY test method.
	// Use it to set up data that multiple tests share,
	// so you don't repeat setup code in every test.
	@BeforeEach
	void setUp() {
		testStudent = new Student("Alice", "alice@email.com", "S101");
	}

	// --- TEST 1 ---
	@Test
	// Method name IS the test description — be descriptive, no need for camelCase
	void addStudent_shouldSaveAndReturnStudent() {
		// ARRANGE
		// Tell the fake repository: "when save() is called with any Student,
		// return our testStudent." We're not testing the repo — just the service.
		when(studentRepository.save(any(Student.class))).thenReturn(testStudent);

		// ACT — call the actual method we're testing
		Student result = studentService.addStudent(testStudent);

		// ASSERT — verify the result is correct
		assertNotNull(result); // result should not be null
		assertEquals("Alice", result.getName()); // name should be Alice
		assertEquals("alice@email.com", result.getEmail()); // email should match

		// Verify the repository's save() was actually called once
		// (confirms the service didn't skip calling save for some reason)
		verify(studentRepository, times(1)).save(testStudent);
	}

	// --- TEST 2 ---
	@Test
	void getAllStudents_shouldReturnListOfStudents() {
		// ARRANGE
		Student student2 = new Student("Bob", "bob@email.com", "S102");
		// Tell the fake repository: when findAll() is called, return this list
		when(studentRepository.findAll()).thenReturn(List.of(testStudent, student2));

		// ACT
		List<Student> results = studentService.getAllStudents();

		// ASSERT
		assertNotNull(results);
		assertEquals(2, results.size()); // should have 2 students
		assertEquals("Alice", results.get(0).getName()); // first should be Alice
		assertEquals("Bob", results.get(1).getName()); // second should be Bob

		verify(studentRepository, times(1)).findAll();
	}
}