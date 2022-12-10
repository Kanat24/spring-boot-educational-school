package ru.hogwarts.scool.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.scool.StudentNotFoundException;
import ru.hogwarts.scool.model.Faculty;
import ru.hogwarts.scool.model.Student;
import ru.hogwarts.scool.repositories.StudentRepositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepositories studentRepositories;

    public StudentService(StudentRepositories studentRepositories) {
        this.studentRepositories = studentRepositories;
    }

    public Collection<Student> getAll() {
        return studentRepositories.findAll();
    }
    public Student createStudent(Student student) {
        return studentRepositories.save(student);
    }
    public Student findStudent(Long id){
        return studentRepositories.findById(id).get();
    }
    public Student editStudent(Long id, Student student){
        return studentRepositories.save(student);
    }
    public void deleteStudent(Long id){
       studentRepositories.deleteById(id);
    }
    public Collection<Student> findByAge(int age) {
        return studentRepositories.findAll().stream().filter(s->s.getAge()==age).collect(Collectors.toList());
    }

}
