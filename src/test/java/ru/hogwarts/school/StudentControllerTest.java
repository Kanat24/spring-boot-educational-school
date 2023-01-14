package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.util.UriComponentsBuilder;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.net.URI;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
    private Long testFacultyId;

    private final TestRestTemplate restTemplate = new TestRestTemplate();
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    FacultyRepository facultyRepository;
    @LocalServerPort
    private int port;

    @BeforeEach
    public void cleanUp() {
        studentRepository.deleteAll();
        facultyRepository.deleteAll();
        Faculty testFaculty = new Faculty();
        testFaculty.setName("Test");
        testFaculty.setColor("Test");
        testFacultyId = facultyRepository.save(testFaculty).getId();
    }

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(studentRepository.findAll()).isNotNull();
    }

    @Test
    public void createStudentTest() throws Exception {
        Student studentTest = new Student("Test", 20);
        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port +
                "/", studentTest, Student.class)).isNotNull();

    }

    @Test
    public void getStudentByIdTest() throws Exception {
        Student student = new Student("Test", 20);
        Long id = this.studentRepository.save(student).getId();
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/"
                + id, Student.class)).isNotNull();
        Assertions.assertThat(this.restTemplate.getForEntity("http://localhost:" + port + "/"
                + id, Student.class).getBody()).isEqualTo(student);

    }

    @Test
    public void deleteStudentByIdTest() {
        Student studentTest = new Student();
        studentTest.setName("Test");
        studentTest.setAge(25);
        studentTest.setFaculty(this.facultyRepository.getReferenceById(testFacultyId));
        Long id = this.studentRepository.save(studentTest).getId();
        org.junit.jupiter.api.Assertions.assertEquals(1, studentRepository.findAll().size());
        restTemplate.delete("http://localhost:" + port + "/student/" + id);
        org.junit.jupiter.api.Assertions.assertTrue(this.studentRepository.findAll().isEmpty());
    }

    @Test
    public void putStudentByIdTest() {
        Student studentTest = new Student();
        studentTest.setName("Test");
        studentTest.setAge(25);
        Long id = this.studentRepository.save(studentTest).getId();
        org.junit.jupiter.api.Assertions.assertEquals(1, studentRepository.findAll().size());
        studentTest.setName("Ivan");
        studentTest.setAge(10);
        restTemplate.put("http://localhost:" + port + "/student/" + id, studentTest);
        Assertions.assertThat(studentTest.getName().equals("Ivan"));
    }


    private UriComponentsBuilder getUriBuilder() {
        return UriComponentsBuilder.newInstance().scheme("http").host("localhost").port(port).path("/hogwarts/student");
    }

    private ResponseEntity<Student> whenCreateStudent(URI uri, Student student) {
        return restTemplate.postForEntity(uri, student, Student.class);
    }

    private Student givenStudent(String name, int age) {
        return new Student(name, age);
    }

    private void thenStudentHasBeenCreated(ResponseEntity<Student> response) {
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isNotNull();

    }
}
