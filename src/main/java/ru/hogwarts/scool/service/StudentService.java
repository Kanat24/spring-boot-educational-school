package ru.hogwarts.scool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.scool.model.Student;
import ru.hogwarts.scool.repositories.FacultyRepository;
import ru.hogwarts.scool.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import java.util.stream.Collectors;

@Service
public class StudentService {
    public static final Object flag = new Object();
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository,
                          FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public Collection<Student> getAll() {
        logger.info("method called getAll()");
        return studentRepository.findAll();
    }

    public Student createStudent(Student student) {
        logger.info("method called createStudent()");
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        logger.info("method called findStudent()");
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Long id, Student student) {
        logger.info("method called editStudent()");
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        logger.info("method called deleteStudent()");
        studentRepository.deleteById(id);
    }

    public Collection<Student> findByAge(int age) {
        logger.info("method called findByAge()");
        return studentRepository.findAll().stream().filter(s -> s.getAge() == age).collect(Collectors.toList());
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("method called findByAgeBetween()");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Collection<Student> findStudentsByFacultyId(Long faculty_id) {
        logger.info("method called findStudentsByFacultyId()");
        return studentRepository.findStudentsByFacultyId(faculty_id);
    }

    public Integer getAllByName() {
        logger.info("method called getAllByName()");
        return studentRepository.getAllByName();
    }

    public Integer findByAge() {
        logger.info("method called findByAge()");
        return studentRepository.findByAge();
    }

    public Set<Student> getStudentsById() {
        logger.info("method called getStudentsById()");
        return studentRepository.findStudentsById();
    }

    public Collection<Student> findByNameIgnoreCase(String name) {
        logger.info("method called findByNameIgnoreCase()");
        return studentRepository.findByNameIgnoreCase(name);
    }

    public List<Student> getAllStudentsAndName() {
        List<Student> students = studentRepository.findAll();
        students.stream().map(Student::getName).limit(2).forEach(System.out::println);
        Thread thread1 = new Thread(() -> {
            students.stream().map(Student::getName).skip(2).limit(1).forEach(System.out::println);
            students.stream().map(Student::getName).skip(3).limit(1).forEach(System.out::println);
        });
        Thread thread2 = new Thread(() -> {
            students.stream().map(Student::getName).skip(4).limit(1).forEach(System.out::println);
            students.stream().map(Student::getName).skip(5).limit(1).forEach(System.out::println);
        });
        thread1.start();
        thread2.start();
        return students;
    }

    public List<Student> getAllStudentsAndNameSynchronized(){
        List<Student> students2 = studentRepository.findAll();
        methodSynchronized(students2.stream().map(Student::getName).limit(1).collect(Collectors.joining()));
        methodSynchronized(students2.stream().map(Student::getName).skip(1).limit(1).collect(Collectors.joining(",")));
        Thread thread1 = new Thread(() -> {
            methodSynchronized(students2.stream().map(Student::getName).skip(2).limit(1).collect(Collectors.joining(",")));
            methodSynchronized(students2.stream().map(Student::getName).skip(3).limit(1).collect(Collectors.joining(",")));
        });
        Thread thread2 = new Thread(() -> {
            methodSynchronized(students2.stream().map(Student::getName).skip(4).limit(1).collect(Collectors.joining(",")));
            methodSynchronized(students2.stream().map(Student::getName).skip(5).limit(1).collect(Collectors.joining(",")));
        });
        thread1.start();
        thread2.start();
        return students2;
    }

    public static void methodSynchronized(String name){
        synchronized (flag){
            System.out.println(name);
        }
    }

}
