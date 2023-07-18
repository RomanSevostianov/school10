package ru.hogwarts.school10.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school10.model.Faculty;
import ru.hogwarts.school10.model.Student;
import java.util.Collection;

public interface StudentRepositoriy extends JpaRepository<Student, Long> {

    Collection<Student> findAll(Student student);

    Collection<Student> findAgeStudent(Integer age);

    Collection<Student> findByAgeBeetweenMinAndMaxGreaterThan(Integer minAge, Integer maxAge);

    Collection<Student> findByStudentOrderByFacultyId(Faculty faculty);

}
