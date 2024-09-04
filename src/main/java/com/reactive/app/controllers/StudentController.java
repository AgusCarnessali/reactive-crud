package com.reactive.app.controllers;

import com.reactive.app.models.Student;
import com.reactive.app.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @PostMapping
    @Operation(summary = "Save a student", description = "Saves a new student to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student saved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    Mono<Student> save(@RequestBody Student student) {
        return service.save(student);
    }

    @GetMapping
    @Operation(summary = "Get all students", description = "Retrieves all students from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of students")
    })
    Flux<Student> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get student by ID", description = "Retrieves a student by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved student"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public Mono<Student> findById(@Parameter(description = "ID of the student to be retrieved") @PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete student by ID", description = "Deletes a student by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public Mono<Void> deleteById(@Parameter(description = "ID of the student to be deleted") @PathVariable("id") Integer id) {
        return service.deleteById(id);
    }

}
