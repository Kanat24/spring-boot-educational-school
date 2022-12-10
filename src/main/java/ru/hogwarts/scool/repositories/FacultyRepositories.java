package ru.hogwarts.scool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.scool.model.Faculty;

public interface FacultyRepositories extends JpaRepository<Faculty, Long> {
}
