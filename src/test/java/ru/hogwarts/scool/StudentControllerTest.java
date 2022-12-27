//package ru.hogwarts.scool;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.engine.execution.JupiterEngineExecutionContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.web.util.UriComponentsBuilder;
//import ru.hogwarts.scool.controller.StudentController;
//import ru.hogwarts.scool.model.Faculty;
//import ru.hogwarts.scool.model.Student;
//import ru.hogwarts.scool.repositories.FacultyRepository;
//import ru.hogwarts.scool.repositories.StudentRepository;
//
//import java.net.URI;
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class StudentControllerTest {
//    private Long testFacultyId;
//
//   private final TestRestTemplate restTemplate =new TestRestTemplate();
//    @Autowired
//     StudentRepository studentRepository;
//    @Autowired
//     FacultyRepository facultyRepository;
//    @LocalServerPort
//    private int port;
//
//    @BeforeEach
//    public void cleanUp(){
//        studentRepository.deleteAll();
//        facultyRepository.deleteAll();
//        Faculty testFaculty = new Faculty();
//        testFaculty.setName("Test");
//        testFaculty.setColor("Test");
//        testFacultyId= facultyRepository.save(testFaculty).getId();
//    }
//
//    @Test
//    public void contextLoads() {
//    //    Assertions.assertThat(studentRepository.findAll()).isNotNull();
//    }
//
//    @Test
//    public void createStudentTest() throws Exception {
//        Student student = new Student("Test", 20);
//        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:"+ port+
//                "/", student, Student.class)).isNotNull();
//    }
//    @Test
//    public void getStudentByIdTest() throws Exception {
//        Student student = new Student("Test", 20);
//        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/"
//                + student.getId(), Student.class)).isNotNull();
//    }
//    @Test
//    public void deleteStudentByIdTest(){
//        Student studentTest = new Student();
//        studentTest.setName("Test");
//        studentTest.setAge(25);
//        studentTest.setFaculty(this.facultyRepository.getReferenceById(testFacultyId));
//    Long id = this.studentRepository.save(studentTest).getId();
//       org.junit.jupiter.api.Assertions.assertEquals(1, studentRepository.findAll().size());
//        this.restTemplate.delete("http://localhost:" + port + "/student"+ id);
//        org.junit.jupiter.api.Assertions.assertTrue(this.studentRepository.findAll().isEmpty());
//    }
//
//    public void CreateStudentTest() {
//        Student student = givenStudent("Test", 20);
//        ResponseEntity<Student> response = whenCreateStudent(getUriBuilder().build().toUri(), student);
//        thenStudentHasBeenCreated(response);
//    }
//    @Test
//    public void getStudentBuIdTest(){
//        Student student = givenStudent("Test", 20);
//        ResponseEntity<Student> createResponse = whenCreateStudent(getUriBuilder().build().toUri(), student);
//        thenStudentHasBeenCreated(createResponse);
//        Student createdStudent = createResponse.getBody();
//
//
//    }
//
//    private UriComponentsBuilder getUriBuilder() {
//        return UriComponentsBuilder.newInstance().scheme("http").host("localhost").port(port).path("/hogwarts/student");
//    }
//
//    private ResponseEntity<Student> whenCreateStudent(URI uri, Student student) {
//        return restTemplate.postForEntity(uri, student, Student.class);
//    }
//
//    private Student givenStudent(String name, int age) {
//        return new Student(name, age);
//    }
//
//    private void thenStudentHasBeenCreated(ResponseEntity<Student> response) {
//       Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        Assertions.assertThat(response.getBody()).isNotNull();
//        Assertions.assertThat(response.getBody().getId()).isNotNull();
//
//    }
//}
