package com.reactive.app.controllers;

import com.reactive.app.models.Student;
import com.reactive.app.services.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class StudentControllerTest {
    @Mock
    private StudentService service;

    @InjectMocks
    private StudentController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveStudentSuccessfully() {
        Student student = new Student(1, "John", "Doe", 20);
        when(service.save(any(Student.class))).thenReturn(Mono.just(student));

        Mono<Student> result = controller.save(student);

        StepVerifier.create(result)
                .expectNext(student)
                .verifyComplete();
    }

    @Test
    void saveStudentWithInvalidInput() {
        when(service.save(null)).thenReturn(Mono.error(new IllegalArgumentException("Invalid input")));

        Mono<Student> result = controller.save(null);

        StepVerifier.create(result)
                .expectError(IllegalArgumentException.class)
                .verify();
    }

    @Test
    void findAllStudentsSuccessfully() {
        Student student1 = new Student(1, "John", "Doe", 20);
        Student student2 = new Student(2, "Jane", "Doe", 22);
        when(service.findAll()).thenReturn(Flux.just(student1, student2));

        Flux<Student> result = controller.findAll();

        StepVerifier.create(result)
                .expectNext(student1)
                .expectNext(student2)
                .verifyComplete();
    }

    @Test
    void findAllStudentsEmpty() {
        when(service.findAll()).thenReturn(Flux.empty());

        Flux<Student> result = controller.findAll();

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void findStudentByIdSuccessfully() {
        Student student = new Student(1, "John", "Doe", 20);
        when(service.findById(1)).thenReturn(Mono.just(student));

        Mono<Student> result = controller.findById(1);

        StepVerifier.create(result)
                .expectNext(student)
                .verifyComplete();
    }

    @Test
    void findStudentByIdNotFound() {
        when(service.findById(1)).thenReturn(Mono.empty());

        Mono<Student> result = controller.findById(1);

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void deleteStudentByIdSuccessfully() {
        when(service.deleteById(1)).thenReturn(Mono.empty());

        Mono<Void> result = controller.deleteById(1);

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void deleteStudentByIdNotFound() {
        when(service.deleteById(1)).thenReturn(Mono.error(new IllegalArgumentException("Student not found")));

        Mono<Void> result = controller.deleteById(1);

        StepVerifier.create(result)
                .expectError(IllegalArgumentException.class)
                .verify();
    }
}