package ru.hogwarts.school10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school10.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Collection<Faculty> findByColorContainsIgnoreCase(String color);

}
