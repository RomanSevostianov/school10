package ru.hogwarts.school10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school10.model.Avatar;

import java.util.Optional;

public interface AvatarRepositoriy  extends JpaRepository <Avatar, Long> {

    Optional <Avatar> findStudentByIdAvatar (Long studentId);
}
