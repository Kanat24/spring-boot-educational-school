package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.*;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    public FacultyController(FacultyService facultyService,
                             StudentRepository studentRepository,
                             FacultyRepository facultyRepository) {
        this.facultyService = facultyService;
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    @GetMapping
    public Set<Faculty> findFaculties(@RequestParam(required = false) String color,
                                      @RequestParam(required = false) String name) {
        if (name == null) {
            return facultyService.findFacultyByColor(color);
        }

        return (Set<Faculty>) facultyService.findFacultyByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping("{id}")
    public ResponseEntity<Faculty> editFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(id, faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<Collection<Faculty>> findFaculties(@RequestParam(required = false) String color) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findByColor(color));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/longestName")
    public ResponseEntity<String> longestNameOrFaculty() {
        String longestName = facultyRepository.findAll().stream()
                .map(Faculty::getName).max(Comparator.comparing(String::length)).get();
        return ResponseEntity.ok(longestName);
    }

}
