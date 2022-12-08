package ru.hogwarts.scool.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.scool.FacultyNotFoundException;
import ru.hogwarts.scool.model.Faculty;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long lastId =1;
    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(lastId);
        faculties.put(lastId,faculty);
        lastId++;
        return faculty;
    }
    public Faculty findFaculty(long id){
        if (faculties.containsKey(id)){
        return faculties.get(id);
        }
        else throw new FacultyNotFoundException();
    }
    public Faculty editFaculty(long id, Faculty faculty){
        if (faculties.containsKey(id)) {
            Faculty oldFaculty = faculties.get(id);
            oldFaculty.setName(faculty.getName());
            oldFaculty.setColor(faculty.getColor());
            return oldFaculty;
        }
        faculties.put(id, faculty);
        return faculty;
    }
    public void deleteFaculty(long id){
        if (faculties.containsKey(id)){
         faculties.remove(id);
        }
        else{ throw new FacultyNotFoundException();}
    }
    public Collection<Faculty> findByColor(String color) {
        return faculties.values().stream().filter(s-> Objects.equals(s.getColor(), color)).collect(Collectors.toList());
    }

    public Collection<Faculty> getAll() {
        return faculties.values();
    }
}
