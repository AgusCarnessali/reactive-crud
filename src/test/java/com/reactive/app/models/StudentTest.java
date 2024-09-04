package com.reactive.app.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void createStudentSuccessfully() {
        Student student = new Student(1, "John", "Doe", 20);
        assertEquals(1, student.getId());
        assertEquals("John", student.getName());
        assertEquals("Doe", student.getLastname());
        assertEquals(20, student.getAge());
    }

    @Test
    void setStudentPropertiesSuccessfully() {
        Student student = new Student();
        student.setId(1);
        student.setName("John");
        student.setLastname("Doe");
        student.setAge(20);

        assertEquals(1, student.getId());
        assertEquals("John", student.getName());
        assertEquals("Doe", student.getLastname());
        assertEquals(20, student.getAge());
    }

    @Test
    void studentToString() {
        Student student = new Student(1, "John", "Doe", 20);
        String expected = "Student{id=1, name='John', lastname='Doe', age=20}";
        assertEquals(expected, student.toString());
    }

    @Test
    void createStudentWithBuilder() {
        Student student = Student.builder()
                .id(1)
                .name("John")
                .lastname("Doe")
                .age(20)
                .build();

        assertEquals(1, student.getId());
        assertEquals("John", student.getName());
        assertEquals("Doe", student.getLastname());
        assertEquals(20, student.getAge());
    }

    @Test
    void createStudentWithNoArgsConstructor() {
        Student student = new Student();
        assertNull(student.getId());
        assertNull(student.getName());
        assertNull(student.getLastname());
        assertEquals(0, student.getAge());
    }
}