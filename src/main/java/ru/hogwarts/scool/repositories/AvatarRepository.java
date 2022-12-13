package ru.hogwarts.scool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.scool.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Optional <Avatar> findAvatarByStudentId(Long student_id);

}
