package ru.hogwarts.school10.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school10.model.Faculty;
import ru.hogwarts.school10.model.Student;
import ru.hogwarts.school10.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;


@Service
public class StudentService {
    private final StudentRepository studentRepositoriy;


    public StudentService(StudentRepository studentRepositoriy) {
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

    public Collection<Student> getStudentAge(Integer age) {
        return studentRepositoriy.findAgeStudent(age);
    }

    public Collection<Student> getStudentAgeBeetween(Integer minAge, Integer maxAge) {
        return studentRepositoriy.findByAgeBeetweenMinAndMaxGreaterThan(minAge, maxAge);
    }


    public Collection<Student> getAllStudentFaculty(Faculty faculty) {
        return studentRepositoriy.findByStudentOrderByFacultyId(faculty);

    }





    public List<Student> getLastStudent(int count) {
        return studentRepositoriy.getLastStudent(count);
    }

    public int getCountOfStudent() {
        return studentRepositoriy.getCountOfStudent();
    }

    public double getAverageAgeOfStudent() {
        return studentRepositoriy.getAverageAgeOfStudent();
    }
}
