package com.shashwat.classmanager;

import com.shashwat.classmanager.model.*;
import com.shashwat.classmanager.repository.*;
import com.shashwat.classmanager.service.SubmissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubmissionServiceTest {

    @Mock
    private SubmissionRepository submissionRepository;

    @Mock
    private AssignmentRepository assignmentRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private SubmissionService submissionService;

    private Student alice;
    private Student bob;
    private Faculty faculty;
    private ClassRoom classRoom;
    private Assignment assignment;

    @BeforeEach
    void setUp() {
        alice = new Student("Alice", "alice@email.com", "S101");
        bob = new Student("Bob", "bob@email.com", "S102");
        faculty = new Faculty("Dr. Smith", "smith@email.com", "Computer Science");

        // ClassRoom with both students enrolled
        classRoom = new ClassRoom("Math101", faculty);
        classRoom.addStudent(alice);
        classRoom.addStudent(bob);

        // Assignment due in the future
        assignment = new Assignment("HW1", LocalDate.of(2026, 7, 10), classRoom);
    }

    // --- TEST 1 ---
    @Test
    void submitAssignment_shouldCreateAndReturnSubmission() {
        // ARRANGE
        when(assignmentRepository.findById(1L)).thenReturn(Optional.of(assignment));
        when(studentRepository.findById(1L)).thenReturn(Optional.of(alice));
        when(submissionRepository.save(any(Submission.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        // .thenAnswer is used here instead of .thenReturn because we want to return
        // the ACTUAL submission object that was passed into save(), not a hardcoded one.
        // invocation.getArgument(0) means "give me back the first argument that was passed in."
        // This lets us inspect the real Submission object the service created.

        // ACT
        Submission result = submissionService.submitAssignment(1L, 1L);

        // ASSERT
        assertNotNull(result);
        assertEquals(alice, result.getStudent());
        assertEquals(assignment, result.getAssignment());
        assertNotNull(result.getSubmittedOn()); // date was set automatically
        assertFalse(result.isLate()); // submitted today, due July 10 — not late
        verify(submissionRepository, times(1)).save(any(Submission.class));
    }

    // --- TEST 2 ---
    @Test
    void getStudentsWhoSubmitted_shouldReturnOnlySubmittedStudents() {
        // ARRANGE
        // Only Alice submitted
        Submission aliceSubmission = new Submission(assignment, alice, LocalDate.now());
        when(submissionRepository.findByAssignmentId(1L))
                .thenReturn(List.of(aliceSubmission));

        // ACT
        List<Student> result = submissionService.getStudentsWhoSubmitted(1L);

        // ASSERT
        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getName());
    }

    // --- TEST 3 — the most important one ---
    @Test
    void getStudentsWhoDidNotSubmit_shouldReturnOnlyMissingStudents() {
        // ARRANGE
        // Alice submitted, Bob did not
        Submission aliceSubmission = new Submission(assignment, alice, LocalDate.now());

        when(assignmentRepository.findById(1L)).thenReturn(Optional.of(assignment));
        when(submissionRepository.findByAssignmentId(1L))
                .thenReturn(List.of(aliceSubmission));

        // ACT
        List<Student> result = submissionService.getStudentsWhoDidNotSubmit(1L);

        // ASSERT
        assertEquals(1, result.size()); // only 1 student missing
        assertEquals("Bob", result.get(0).getName()); // that student is Bob
        // Alice should NOT be in the missing list
        assertFalse(result.stream().anyMatch(s -> s.getName().equals("Alice")));
    }

    // --- TEST 4 ---
    @Test
    void submitAssignment_whenAssignmentNotFound_shouldThrowException() {
        // ARRANGE
        // Tell the mock to return empty Optional — simulates "not found in DB"
        when(assignmentRepository.findById(999L)).thenReturn(Optional.empty());

        // ACT + ASSERT
        // assertThrows verifies that calling this code throws the expected exception
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            submissionService.submitAssignment(999L, 1L);
        });

        assertEquals("Assignment not found with id: 999", exception.getMessage());
    }
}