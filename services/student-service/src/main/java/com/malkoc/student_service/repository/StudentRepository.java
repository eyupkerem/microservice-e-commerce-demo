package com.malkoc.student_service.repository;

import com.malkoc.student_service.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    private final List<Student> studentList = new ArrayList<>();

    public Student addStudent(Student student) {
        studentList.add(student);
        return student;
    }

    public Student findById(Long id) {
        return studentList.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public List<Student> findAll() {
        return studentList;
    }

    public List<Student> findByLessonId(Long lessonId) {
        return studentList.stream().filter(s-> s.getLessonId().equals(lessonId)).toList();
    }



}
