package ru.hogwarts.school10.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school10.model.Faculty;
import ru.hogwarts.school10.model.Student;
import ru.hogwarts.school10.services.StudentServices;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private StudentServices studentServices;

    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    @GetMapping("{age}")
    public ResponseEntity<Student> findStudentAge (@PathVariable int age){
        studentServices.getStudentAge(age);
        return ResponseEntity.ok().build();

    }

    @GetMapping("{id}")
    public ResponseEntity getStudentInfo(@PathVariable long id) {
        studentServices.getStudentId(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public Student postStudent(@RequestBody Student student) {
        return studentServices.createStudent(student);

    }

    @PutMapping
    public ResponseEntity<Student> putStudent(@RequestBody Student student) {
        studentServices.editStudent(student);
        return ResponseEntity.ok().build();
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Student> removeStudent(@PathVariable long id) {
        studentServices.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Student> findAllStudent(@RequestBody Student student) {
        studentServices.getAllStudent(student);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity findAgeBeetWeen(@RequestParam Integer min, @RequestParam Integer max) {
        if (min != null && max != null) {
            return ResponseEntity.ok(findAgeBeetWeen(min, max));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{faculty}")
    public ResponseEntity findAllStudentFaculty(@PathVariable Faculty faculty) {
        studentServices.getAllStudentFaculty(faculty);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/count")
    public int getCountOfStudent() {
        return studentServices.getCountOfStudent();
    }

    @GetMapping("/avarageAge")
    public double getAverageAgeOfStudent() {
        return studentServices.getAverageAgeOfStudent();
    }


    @GetMapping("/lastStudent")
    public List<Student> getLastStudent(@RequestParam (value = "count",defaultValue = "5")int count) {
        return studentServices.getLastStudent(Math.abs(count));
    }
}
