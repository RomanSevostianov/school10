package ru.hogwarts.school10.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school10.model.Student;
import ru.hogwarts.school10.services.StudentServices;

@RestController
@RequestMapping("student")
public class StudentController {

private StudentServices studentServices;

    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    @GetMapping("{id}")
    public ResponseEntity getStudentInfo (@PathVariable long id){
        studentServices.getStudentId(id);
      return ResponseEntity.ok().build();
}

@PostMapping
    public Student postStudent (@RequestBody Student student){
       return studentServices.createStudent(student);

}

@PutMapping
    public ResponseEntity<Student> putStudent (@RequestBody Student student){
studentServices.editStudent(student);
return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")

    public ResponseEntity <Student> removeStudent (@PathVariable long id){
studentServices.deleteStudent(id);
return  ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity <Student> findAllStudent (@RequestBody Student student){
        studentServices.getAllStudent(student);
       return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity findAgeBeetWeen (@RequestParam Integer min, @RequestParam Integer max){
        if (min != null && max!=null){
          return  ResponseEntity.ok(findAgeBeetWeen(min,max));
        }
return ResponseEntity.notFound().build();
    }
}
