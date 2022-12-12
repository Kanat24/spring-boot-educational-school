package ru.hogwarts.scool.service;

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

    public FacultyService(FacultyRepository facultyRepository,
                          StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
      return facultyRepository.save(faculty);
    }
    public Faculty findFaculty(long id){
        return facultyRepository.findById(id).get();
    }
    public Faculty editFaculty(long id, Faculty faculty){
        return facultyRepository.save(faculty);
    }
    public void deleteFaculty(long id){
        facultyRepository.deleteById(id);
        }
    public Collection<Faculty> findByColor(String color) {
        return facultyRepository.findAll().stream().filter(s-> Objects.equals(s.getColor(), color)).collect(Collectors.toList());
    }

    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }
    public Faculty findFacultyByColorIgnoreCaseOrNameIgnoreCase(String color, String name){
        return facultyRepository.findFacultyByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

}
