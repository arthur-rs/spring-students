package com.students.services;

import com.students.dtos.CreateStudentDto;
import com.students.dtos.UpdateStudentDto;
import com.students.exceptions.StudentNotFoundException;
import com.students.models.Student;
import com.students.repositories.StudentsRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class StudentsService {

    private final StudentsRepository studentsRepository;

    @Autowired
    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @Transactional
    public Student createStudent(@NonNull CreateStudentDto dto) {
        Student student = Student.builder()
                .name(dto.getName())
                .score(dto.getScore())
                .build();
        this.studentsRepository.save(student);
        return student;
    }

    public Student findStudentById(UUID id) {
        return studentsRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public List<Student> findAllStudents() {
        return this.studentsRepository.findAll();
    }

    @Transactional
    public Student updateStudent(UUID id, @NonNull UpdateStudentDto dto) {
        Student student = this.findStudentById(id);
        dto.getName().ifPresent(student::setName);
        dto.getScore().ifPresent(student::setScore);
        this.studentsRepository.save(student);
        return student;
    }

    @Transactional
    public void deleteStudent(UUID id) {
        Student student = this.findStudentById(id);
        this.studentsRepository.delete(student);
    }
}
