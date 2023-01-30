package me.ildar.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UniversityTest {
    private final Student student = new Student("Евгений", true);
    @Mock
    private StudentValueGenerator studentValueGenerator;
    @InjectMocks
    private University university;


    @Test
    public void getAllStudents() {
        assertNotNull(studentValueGenerator);
        Mockito.when(studentValueGenerator.generateAge()).thenReturn(50);

        university.addStudents(student);
        List<Student> expected = university.getAllStudents();

        assertEquals(expected.get(0).getAge(), 50);
    }

    @Test
    public void getAllStudentsOver50Years() {
        Mockito.when(studentValueGenerator.generateAgeInRange(anyInt(),anyInt())).thenReturn(55);

        university.addStudentsInRange(student, 50, 100);
        List<Student> expected = university.getAllStudents();

        assertEquals(expected.get(0).getAge(), 55);
    }
    @Test
    public void getAllStudentsWithCountAgeGenerate() {
        Mockito.when(studentValueGenerator.generateAge()).thenReturn(50);

        university.addStudents(student);
        List<Student> expected = university.getAllStudents();

        assertEquals(expected.get(0).getAge(), 50);

        Mockito.verify(studentValueGenerator, times(2)).generateAge();
    }
    @Test
    public void getAllStudentsInOrder() {
        Mockito.when(studentValueGenerator.generateAgeInRange(anyInt(),anyInt())).thenReturn(55);

        university.addStudentsInRange(student, 50, 100);

        InOrder inOrder = Mockito.inOrder(studentValueGenerator);

        List<Student> expected = university.getAllStudents();
        inOrder.verify(studentValueGenerator, times(2)).generateAge();
        inOrder.verify(studentValueGenerator).generateAgeInRange(anyInt(), anyInt());

        assertEquals(expected.get(0).getAge(), 55);
    }
}