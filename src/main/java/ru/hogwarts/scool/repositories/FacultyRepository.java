package ru.hogwarts.scool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.scool.model.Faculty;
import ru.hogwarts.scool.model.Student;

import java.util.Collection;
import java.util.Set;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Set<Faculty> findFacultyByColorIgnoreCaseOrNameIgnoreCase(String color, String name);

    Set<Faculty> findFacultiesByColor(String color);
}
