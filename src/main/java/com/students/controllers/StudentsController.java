package com.students.controllers;

import com.students.dtos.CreateStudentDto;
import com.students.dtos.UpdateStudentDto;
import com.students.models.Student;
import com.students.services.StudentsService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/v1/students")
public class StudentsController {

    private final StudentsService studentsService;

    @Autowired
    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody @Valid CreateStudentDto dto) {
        Student student = this.studentsService.createStudent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable(value = "id") String id) {
        Student student = this.studentsService.findStudentById(UUID.fromString(id));
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<Iterable<Student>> findAllStudents() {
        Iterable<Student> students = this.studentsService.findAllStudents();
        return ResponseEntity.ok(students);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable(value = "id") String id,
            @RequestBody @Valid UpdateStudentDto dto
    ) {
        Student student = this.studentsService.updateStudent(UUID.fromString(id), dto);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable @NonNull String id) {
        this.studentsService.deleteStudent(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
