package ru.hogwarts.school10.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school10.model.Faculty;
import ru.hogwarts.school10.repositories.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {
    public FacultyService(FacultyRepository facultyRepositoriy) {
        this.facultyRepositoriy = facultyRepositoriy;
    }

    private final FacultyRepository facultyRepositoriy;

    public Faculty getFaculty(long id) {
        return facultyRepositoriy.getReferenceById(id);
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepositoriy.save(faculty);
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepositoriy.save(faculty);
    }

    public void removeFaculty(long id) {
        facultyRepositoriy.deleteById(id);
    }

    public Collection<Faculty> findAllFaculty() {
        return facultyRepositoriy.findAll();
    }

    public Collection<Faculty> findColorFaculty(String color) {
        return facultyRepositoriy.findByColorContainsIgnoreCase(color);
    }


}
