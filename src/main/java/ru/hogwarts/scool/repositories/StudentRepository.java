package ru.hogwarts.scool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.scool.model.Faculty;
import ru.hogwarts.scool.model.Student;

import java.util.Collection;
import java.util.Collections;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int min, int max);

    Collection<Student> findStudentsByFacultyId(Long faculty_id);
}
