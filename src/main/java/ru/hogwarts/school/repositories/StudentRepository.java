package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Set;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int min, int max);

    Collection<Student> findStudentsByFacultyId(Long faculty_id);

    @Query(value = "SELECT COUNT(*) From student", nativeQuery = true)
    Integer getAllByName();

    @Query(value = "SELECT avg(age) From student", nativeQuery = true)
    Integer findByAge();

    @Query(value = "SELECT * From student order by id desc limit 5", nativeQuery = true)
    Set<Student> findStudentsById();

    Collection<Student> findByNameIgnoreCase(String name);
}
