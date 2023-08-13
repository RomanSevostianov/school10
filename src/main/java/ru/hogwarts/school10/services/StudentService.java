package ru.hogwarts.school10.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school10.model.Faculty;
import ru.hogwarts.school10.model.Student;
import ru.hogwarts.school10.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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


    public List<String> getStudentByFirstLetter(String letter) {
        return studentRepositoriy.findAll().stream()
                .map(Student::getName)
                .filter(name -> name.substring(0, 1).equalsIgnoreCase(letter))
                .sorted()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public double getAvaregeAgeByStudent() {
        return studentRepositoriy.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
    }

    public Integer getStrange() {
        return Stream.iterate(1, a -> a + 1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
    }
}
