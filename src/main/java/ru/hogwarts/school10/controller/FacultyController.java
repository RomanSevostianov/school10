package ru.hogwarts.school10.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school10.model.Faculty;
import ru.hogwarts.school10.model.Student;
import ru.hogwarts.school10.services.FacultyServices;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private FacultyServices facultyServices;

    public FacultyController(FacultyServices facultyServices) {
        this.facultyServices = facultyServices;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyId(@PathVariable long id) {
        return ResponseEntity.ok(facultyServices.getFaculty(id));
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyServices.createFaculty(faculty));
    }

    @PutMapping
    public ResponseEntity<Faculty> upDateFaculty(@RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyServices.editFaculty(faculty));
    }

    @DeleteMapping("{id}")
    public void deleteFaculty(@PathVariable long id) {
        facultyServices.removeFaculty(id);
    }


    @GetMapping("{color}")

    public ResponseEntity<Collection<Faculty>> findFacultyColor(@PathVariable String color) {
        return ResponseEntity.ok(facultyServices.findColorFaculty(color));
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> findAllFaculty() {
        return ResponseEntity.ok(facultyServices.findAllFaculty());
    }

    @GetMapping("{faculty}")
    public ResponseEntity<Faculty> findFacultyByStudent(@RequestBody List<Student> student) {
        return ResponseEntity.ok(facultyServices.findFacultyByStudent(student));
    }
}
