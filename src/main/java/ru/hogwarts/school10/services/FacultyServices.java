package ru.hogwarts.school10.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school10.model.Faculty;
import ru.hogwarts.school10.model.Student;
import ru.hogwarts.school10.repositories.FacultyRepositoriy;

import java.util.Collection;
import java.util.List;

@Service
public class FacultyServices {
    public FacultyServices(FacultyRepositoriy facultyRepositoriy) {
        this.facultyRepositoriy = facultyRepositoriy;
    }

    private final FacultyRepositoriy facultyRepositoriy;

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

    public Faculty findFacultyByStudent(List<Student> student) {
        return facultyRepositoriy.findFacultyByStudents(student);
    }

}
