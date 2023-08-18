package ru.hogwarts.school10.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school10.model.Faculty;
import ru.hogwarts.school10.model.Student;
import ru.hogwarts.school10.services.StudentService;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{age}")
    public ResponseEntity<Student> findStudentAge(@PathVariable int age) {
        studentService.getStudentAge(age);
        return ResponseEntity.ok().build();

    }

    @GetMapping("{id}")
    public ResponseEntity getStudentInfo(@PathVariable long id) {
        studentService.getStudentId(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public Student postStudent(@RequestBody Student student) {
        return studentService.createStudent(student);

    }

    @PutMapping
    public ResponseEntity<Student> putStudent(@RequestBody Student student) {
        studentService.editStudent(student);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Student> removeStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Student> findAllStudent(@RequestBody Student student) {
        studentService.getAllStudent(student);
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
        studentService.getAllStudentFaculty(faculty);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/count")
    public int getCountOfStudent() {
        return studentService.getCountOfStudent();
    }

    @GetMapping("/avarageAge")
    public double getAverageAgeOfStudent() {
        return studentService.getAverageAgeOfStudent();
    }


    @GetMapping("/lastStudent")
    public List<Student> getLastStudent(@RequestParam(value = "count", defaultValue = "5") int count) {
        return studentService.getLastStudent(Math.abs(count));
    }


    //Поиск по первой букве студента
    @GetMapping("/firstLetter/{letter}")
    public List<String> getStudentByFirstLetter(@PathVariable("letter") String letter) {
        return studentService.getStudentByFirstLetter(letter);
    }

    @GetMapping("aveAgeStudent")
    public double getAvaregeAgeStudent() {
        return studentService.getAvaregeAgeByStudent ();
    }

    @GetMapping("/threadName")

    public List<String> getThreadName (){
        return studentService.getThreadNameStudent ();
    }
}
