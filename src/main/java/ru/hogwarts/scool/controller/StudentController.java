package ru.hogwarts.scool.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.hogwarts.scool.model.Faculty;
import ru.hogwarts.scool.model.Student;
import ru.hogwarts.scool.repositories.StudentRepository;
import ru.hogwarts.scool.service.StudentService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final StudentRepository studentRepository;

    public StudentController(StudentService studentService,
                             StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> findStudents(@RequestParam int min, @RequestParam int max) {
        if (min < max) {
            return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
//        if (student.getId() != null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(student));
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> editStudent(@PathVariable("id") Long id, @RequestBody Student student) {
        Student foundStudent = studentService.editStudent(id, student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<Collection<Student>> findStudents(@RequestParam(required = false) int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/faculty/{faculty_id}")
    public Collection<Student> findStudentsByFacultyId(@PathVariable Long faculty_id) {
        return studentService.findStudentsByFacultyId(faculty_id);
    }

    @GetMapping("/students/{id}")
    public Faculty findFacultyByStudents(@PathVariable Long id) {
        return studentService.findStudent(id).getFaculty();
    }

    @GetMapping("/students/getAll")
    public Integer getAllByName() {
        return studentService.getAllByName();
    }

    @GetMapping("/student/findByAge")
    public Integer findByAge() {
        return studentService.findByAge();
    }

    @GetMapping("/student/getStudentById")
    public Set<Student> getStudentById() {
        return studentService.getStudentsById();
    }

    @GetMapping("/student/name/{name}")
    ResponseEntity<Collection<Student>> findByNameIgnoreCase(@PathVariable("name") String name) {
        Collection<Student> students = studentService.findByNameIgnoreCase(name);
        return ResponseEntity.ok(students);
    }
    @GetMapping("/student/findAllName/")
    ResponseEntity <String> findAllNameStudents(){
        String studentsName = studentRepository.findAll().stream()
                .map(Student::getName).map(String::toUpperCase)
                .filter(name -> (name.charAt(0)=='A'))
                .sorted().collect(Collectors.joining(", "));
        return ResponseEntity.ok(studentsName);
    }
    @GetMapping("/student/getAverageAge/")
    ResponseEntity<Double> findAverageAgeStudents(){
        double averageAge= studentRepository.findAll().stream()
                .mapToInt(Student::getAge).summaryStatistics().getAverage();
        return ResponseEntity.ok(averageAge);
    }
    @GetMapping("/returnInteger")
    Integer returnInteger(){
        int sum = Stream.iterate(1, a -> a +1).limit(1_000_000).parallel().reduce(0, (a, b) -> a + b );
        return sum;
    }
}