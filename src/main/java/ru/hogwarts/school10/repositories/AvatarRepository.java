package ru.hogwarts.school10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school10.model.Avatar;

import java.util.List;
import java.util.Optional;

public interface AvatarRepository extends JpaRepository <Avatar, Long> {

    Optional <Avatar> findAvatarByIdStudent(Long Id);

    List<Avatar> findAllAvatar (Integer pageNumber, Integer numberSize);
}
