package ru.hogwarts.scool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.scool.model.Faculty;
import ru.hogwarts.scool.model.Student;
import ru.hogwarts.scool.repositories.FacultyRepository;
import ru.hogwarts.scool.repositories.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(FacultyService.class);


    public FacultyService(FacultyRepository facultyRepository,
                          StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("method called createFaculty()");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.info("method called findFaculty()");
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(long id, Faculty faculty) {
        logger.info("method called editFaculty()");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("method called deleteFaculty()");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByColor(String color) {
        logger.info("method called findByColor()");
        return facultyRepository.findAll().stream().filter(s -> Objects.equals(s.getColor(), color)).collect(Collectors.toList());
    }

    public Collection<Faculty> getAll() {
        logger.info("method called getAll()");
        return facultyRepository.findAll();
    }

    public Set<Faculty> findFacultyByColorIgnoreCaseOrNameIgnoreCase(String color, String name) {
        logger.info("method called findFacultyByColorIgnoreCaseOrNameIgnoreCase()");
        return facultyRepository.findFacultyByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

    public Set<Faculty> findFacultyByColor(String color) {
        logger.info("method called findFacultiesByColor()");
        return facultyRepository.findFacultiesByColor(color);
    }
}
