package ru.hogwarts.scool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.scool.model.Faculty;
import ru.hogwarts.scool.model.Student;

import javax.persistence.criteria.From;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static org.hibernate.loader.Loader.SELECT;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int min, int max);

    Collection<Student> findStudentsByFacultyId(Long faculty_id);

    @Query(value = "SELECT COUNT(*) From student", nativeQuery = true)
    Integer getAllByName();

    @Query(value = "SELECT avg(age) From student", nativeQuery = true)
    Integer findByAge();

    @Query(value = "SELECT * From student order by id desc limit 5", nativeQuery = true)
    Set<Student> findStudentsById();
}
