package ru.hogwarts.school10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school10.model.Faculty;
import ru.hogwarts.school10.model.Student;

import java.util.Collection;
import java.util.List;

public interface FacultyRepositoriy extends JpaRepository<Faculty, Long> {
    List<Faculty> findAllFaculty(Faculty faculty);

    Collection<Faculty> findByColorContainsIgnoreCase(String color);

    Faculty findFacultyByStudents(Student student);
}
