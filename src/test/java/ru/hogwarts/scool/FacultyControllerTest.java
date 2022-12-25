//package ru.hogwarts.scool;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.json.JSONObject;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.mock.mockito.SpyBean;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MockMvcBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import ru.hogwarts.scool.controller.FacultyController;
//import ru.hogwarts.scool.model.Faculty;
//import ru.hogwarts.scool.repositories.FacultyRepository;
//import ru.hogwarts.scool.repositories.StudentRepository;
//import ru.hogwarts.scool.service.FacultyService;
//import ru.hogwarts.scool.service.StudentService;
//
//import java.util.*;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anySet;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(controllers = FacultyController.class)
//public class FacultyControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private FacultyRepository facultyRepository;
//    @MockBean
//    private StudentRepository studentRepository;
//    @SpyBean
//    private FacultyService facultyService;
//    @SpyBean
//    private StudentService studentService;
//    @InjectMocks
//    private FacultyController facultyController;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    public void getFacultyTest() throws Exception {
//        Long id = 1L;
//        String name = "qqqqqq";
//        String color = "aaaaaa";
//
//        JSONObject facultyObject = new JSONObject();
//        facultyObject.put("id", id);
//        facultyObject.put("name", name);
//        facultyObject.put("color", color);
//
//        Faculty faculty = new Faculty();
//        faculty.setId(id);
//        faculty.setName(name);
//        faculty.setColor(color);
//        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
//        when(facultyRepository.findById(id)).thenReturn(Optional.of(faculty));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/faculty/{id}", id).contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.name").value(name))
//                .andExpect(jsonPath("$.color").value(color));
//    }
//
//    @Test
//    public void findFacultiesByColor() throws Exception {
//        Long id1 = 1L;
//        String name1 = "aaaaa";
//
//        Long id2 = 2L;
//        String name2 = "bbbbb";
//
//        String color = "Yellow";
//
//        Faculty faculty1 = new Faculty();
//        faculty1.setId(id1);
//        faculty1.setName(name1);
//        faculty1.setColor(color);
//
//        Faculty faculty2 = new Faculty();
//        faculty2.setId(id2);
//        faculty2.setName(name2);
//        faculty2.setColor(color);
//
//        when(facultyRepository.findFacultiesByColor(color)).thenReturn(Set.of(faculty1, faculty2));
//        mockMvc.perform(MockMvcRequestBuilders.get("/faculty").queryParam("color", color)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(List.of(faculty1, faculty2))));
//    }
//
//    @Test
//    public void findFacultiesByColorOrNameIgnoreCase() throws Exception {
//        Long id1 = 1L;
//        String name1 = "aaaaa";
//        String color1 = "Yellow";
//        String color1IgnoreCase = "YelloW";
//
//
//        Long id2 = 2L;
//        String name2 = "bbbbb";
//        String color2 = "Red";
//        String name2IgnoreCase = "BBBBB";
//
//
//        Faculty faculty1 = new Faculty();
//        faculty1.setId(id1);
//        faculty1.setName(name1);
//        faculty1.setColor(color1);
//
//        Faculty faculty2 = new Faculty();
//        faculty2.setId(id2);
//        faculty2.setName(name2);
//        faculty2.setColor(color2);
//        when(facultyRepository.findFacultyByColorIgnoreCaseOrNameIgnoreCase(color1IgnoreCase, name2IgnoreCase)).thenReturn(Set.of(faculty1, faculty2));
//        mockMvc.perform(MockMvcRequestBuilders.get("/faculty")
//                        .queryParam("color", color1IgnoreCase)
//                        .queryParam("name", name2IgnoreCase)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(List.of(faculty1, faculty2))));
//    }
//
//    public void addFacultyTest() throws Exception {
//        Long id = 1L;
//        String name = "qqqqqq";
//        String color = "aaaaaa";
//
//        JSONObject facultyObject = new JSONObject();
//        facultyObject.put("name", name);
//        facultyObject.put("color", color);
//
//        Faculty faculty = new Faculty();
//        faculty.setId(id);
//        faculty.setName(name);
//        faculty.setColor(color);
//        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
//        when(facultyRepository.findById(id)).thenReturn(Optional.of(faculty));
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/faculty")
//                        .content(facultyObject.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.name").value(name))
//                .andExpect(jsonPath("$.color").value(color));
//    }
//
//    @Test
//    public void updateFacultyTest() throws Exception {
//        Long id = 1L;
//        String oldName = "qqqqqq";
//        String oldColor = "aaaaaa";
//
//        String newName = "wwwwww";
//        String newColor = "bbbbbb";
//
//        JSONObject facultyObject = new JSONObject();
//        facultyObject.put("id", id);
//        facultyObject.put("name", newName);
//        facultyObject.put("color", newColor);
//
//        Faculty faculty = new Faculty();
//        faculty.setId(id);
//        faculty.setName(oldName);
//        faculty.setColor(oldColor);
//
//        Faculty updatedFaculty = new Faculty();
//        updatedFaculty.setId(id);
//        updatedFaculty.setName(newName);
//        updatedFaculty.setColor(newColor);
//        when(facultyRepository.findById(id)).thenReturn(Optional.of(faculty));
//        when(facultyRepository.save(any(Faculty.class))).thenReturn(updatedFaculty);
//        mockMvc.perform(MockMvcRequestBuilders
//                        .put("/faculty")
//                        .content(facultyObject.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
////                .andExpect(jsonPath("$.id").value(id))
////                .andExpect(jsonPath("$.name").value(newName))
////                .andExpect(jsonPath("$.color").value(newColor));
//    }
//
//    @Test
//    public void deleteFacultyTest() throws Exception {
//        Long id = 1L;
//        String name = "qqqqqq";
//        String color = "aaaaaa";
//
//        Faculty faculty = new Faculty();
//        faculty.setId(id);
//        faculty.setName(name);
//        faculty.setColor(color);
//        when(facultyRepository.getById(id)).thenReturn(faculty);
//        mockMvc.perform(MockMvcRequestBuilders
//                        .delete("/faculty/{id}", id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
////                .andExpect(jsonPath("$.id").value(id))
////                .andExpect(jsonPath("$.name").value(name))
////                .andExpect(jsonPath("$.color").value(color));
//        verify(facultyRepository, atLeastOnce()).deleteById(id);
//    }
//}
