package com.reactive.app.repositories;

import com.reactive.app.models.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StudentRepository extends ReactiveCrudRepository<Student, Integer> {

}
