package ru.hogwarts.school10.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school10.model.Faculty;
import ru.hogwarts.school10.model.Student;
import ru.hogwarts.school10.repositories.StudentRepositoriy;

import java.util.Collection;


@Service
public class StudentServices {
    private final StudentRepositoriy studentRepositoriy;

    public StudentServices(StudentRepositoriy studentRepositoriy) {
        this.studentRepositoriy = studentRepositoriy;
    }


    public void deleteStudent(long id) {
        studentRepositoriy.deleteById(id);
    }

    public Student getStudentId(long id) {
        return studentRepositoriy.findById(id).get();
    }

    public Student editStudent(Student student) {
        return studentRepositoriy.save(student);
    }

    public Student createStudent(Student student) {
        return studentRepositoriy.save(student);
    }

    public Collection<Student> getAllStudent(Student student) {
        return studentRepositoriy.findAll(student);
    }

    public Collection<Student> getStudentId(Integer age) {
        return studentRepositoriy.findAgeStudent(age);
    }

    public Collection<Student> getStudentAgeBeetween(Integer minAge, Integer maxAge) {
        return studentRepositoriy.findByAgeBeetweenMinAndMaxGreaterThan(minAge, maxAge);
    }


    public Collection<Student> getAllStudentFaculty(Faculty faculty) {
        return studentRepositoriy.findByStudentOrderByFacultyId(faculty);

    }

}
