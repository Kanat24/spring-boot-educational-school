package ru.hogwarts.scool.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.scool.StudentNotFoundException;
import ru.hogwarts.scool.model.Faculty;
import ru.hogwarts.scool.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private long lastId = 1;

    public Collection<Student> getAll() {
        return students.values();
    }
    public Student createStudent(Student student) {
        student.setId(lastId);
        students.put(lastId,student);
        lastId++;
        return student;
    }
    public Student findStudent(Long id){
        if (students.containsKey(id)) {
            return students.get(id);
        }
        else {
            throw new StudentNotFoundException();
        }
    }
    public Student editStudent(Long id, Student student){
        if (students.containsKey(id)) {
            Student oldStudent = this.students.get(id);
            oldStudent.setName(student.getName());
            oldStudent.setAge(student.getAge());
            return oldStudent;
        }
        else
            throw new StudentNotFoundException();
    }
    public void deleteStudent(Long id){
        if (students.containsKey(id)) {
            this.students.remove(id);
        }
        else {
            throw new StudentNotFoundException();
        }
    }
    public Collection<Student> findByAge(int age) {
        return students.values().stream().filter(s->s.getAge()==age).collect(Collectors.toList());
    }
}
