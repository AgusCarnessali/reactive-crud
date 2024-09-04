package com.reactive.app.services;

import com.reactive.app.models.Student;
import com.reactive.app.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveStudentSuccessfully() {
        Student student = new Student();
        when(repository.save(any(Student.class))).thenReturn(Mono.just(student));

        Mono<Student> result = service.save(student);

        StepVerifier.create(result)
                .expectNext(student)
                .verifyComplete();
    }

    @Test
    void saveStudentWithNullValue() {
        when(repository.save(null)).thenReturn(Mono.error(new IllegalArgumentException("Student cannot be null")));

        Mono<Student> result = service.save(null);

        StepVerifier.create(result)
                .expectError(IllegalArgumentException.class)
                .verify();
    }

    @Test
    void findAllStudentsSuccessfully() {
        Student student1 = new Student();
        Student student2 = new Student();
        when(repository.findAll()).thenReturn(Flux.just(student1, student2));

        Flux<Student> result = service.findAll();

        StepVerifier.create(result)
                .expectNext(student1)
                .expectNext(student2)
                .verifyComplete();
    }

    @Test
    void findAllStudentsEmpty() {
        when(repository.findAll()).thenReturn(Flux.empty());

        Flux<Student> result = service.findAll();

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void findStudentByIdSuccessfully() {
        Student student = new Student();
        when(repository.findById(1)).thenReturn(Mono.just(student));

        Mono<Student> result = service.findById(1);

        StepVerifier.create(result)
                .expectNext(student)
                .verifyComplete();
    }

    @Test
    void findStudentByIdNotFound() {
        when(repository.findById(1)).thenReturn(Mono.empty());

        Mono<Student> result = service.findById(1);

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void findStudentByIdInvalidId() {
        when(repository.findById(-1)).thenReturn(Mono.error(new IllegalArgumentException("Invalid ID")));

        Mono<Student> result = service.findById(-1);

        StepVerifier.create(result)
                .expectError(IllegalArgumentException.class)
                .verify();
    }

    @Test
    void deleteStudentByIdSuccessfully() {
        when(repository.deleteById(1)).thenReturn(Mono.empty());

        Mono<Void> result = service.deleteById(1);

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void deleteStudentByIdInvalidId() {
        when(repository.deleteById(-1)).thenReturn(Mono.error(new IllegalArgumentException("Invalid ID")));

        Mono<Void> result = service.deleteById(-1);

        StepVerifier.create(result)
                .expectError(IllegalArgumentException.class)
                .verify();
    }
}