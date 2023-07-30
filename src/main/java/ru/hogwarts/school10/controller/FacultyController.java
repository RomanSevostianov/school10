package ru.hogwarts.school10.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school10.model.Faculty;
import ru.hogwarts.school10.services.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {

   final private FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyId(@PathVariable long id) {
        return ResponseEntity.ok(facultyService.getFaculty(id));
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyService.createFaculty(faculty));
    }

    @PutMapping
    public ResponseEntity<Faculty> upDateFaculty(@RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyService.editFaculty(faculty));
    }

    @DeleteMapping("{id}")
    public void deleteFaculty(@PathVariable long id) {
        facultyService.removeFaculty(id);
    }


    @GetMapping("{color}")

    public ResponseEntity<Collection<Faculty>> findFacultyColor(@PathVariable String color) {
        return ResponseEntity.ok(facultyService.findColorFaculty(color));
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> findAllFaculty() {
        return ResponseEntity.ok(facultyService.findAllFaculty());
    }

}
