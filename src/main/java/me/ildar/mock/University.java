package me.ildar.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class University {
    private Map<Integer, Student> allStudents = new HashMap<>();
    private StudentValueGenerator studentValueGenerator;
    private int countId;

    public University(StudentValueGenerator studentValueGenerator) {
        this.studentValueGenerator = studentValueGenerator;
    }

    public void addStudents(Student student) {
        if (allStudents == null) {
            allStudents = new HashMap<>();
        }
        student.setId(countId);
        studentValueGenerator.generateAge();
        student.setAge(studentValueGenerator.generateAge());
        allStudents.put(countId, student);
        countId++;
    }
    public void addStudentsInRange(Student student, int minAge, int maxAge) {
        if (allStudents == null) {
            allStudents = new HashMap<>();
        }
        student.setId(countId);
        studentValueGenerator.generateAge();
        studentValueGenerator.generateAge();
        student.setAge(studentValueGenerator.generateAgeInRange(minAge,maxAge));
        allStudents.put(countId, student);
        countId++;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(allStudents.values());
    }

    public List<Student> getStudents(boolean isMale) {
        List<Student> listStudents = new ArrayList<>();
        for (Student student : allStudents.values()) {
            if (student.isMale() == isMale) {
                listStudents.add(student);
            }
        }
        return listStudents;
    }

}
