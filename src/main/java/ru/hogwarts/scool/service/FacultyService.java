package ru.hogwarts.scool.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.scool.FacultyNotFoundException;
import ru.hogwarts.scool.model.Faculty;
import ru.hogwarts.scool.repositories.FacultyRepositories;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepositories facultyRepositories;

    public FacultyService(FacultyRepositories facultyRepositories) {
        this.facultyRepositories = facultyRepositories;
    }

    public Faculty createFaculty(Faculty faculty) {
      return facultyRepositories.save(faculty);
    }
    public Faculty findFaculty(long id){
        return facultyRepositories.findById(id).get();
    }
    public Faculty editFaculty(long id, Faculty faculty){
        return facultyRepositories.save(faculty);
    }
    public void deleteFaculty(long id){
        facultyRepositories.deleteById(id);
        }
    public Collection<Faculty> findByColor(String color) {
        return facultyRepositories.findAll().stream().filter(s-> Objects.equals(s.getColor(), color)).collect(Collectors.toList());
    }

    public Collection<Faculty> getAll() {
        return facultyRepositories.findAll();
    }
}
