package ru.hogwarts.school10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.hogwarts.school10.model.Faculty;
import ru.hogwarts.school10.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    void deleteById (Long id);

    Collection<Student> findAll(Student student);

    Collection<Student> findAgeStudent(Integer age);

    Collection<Student> findByAgeBeetweenMinAndMaxGreaterThan(Integer minAge, Integer maxAge);

    Collection<Student> findByStudentOrderByFacultyId(Faculty faculty);


    @Query(value = "SELECT COUNT(s) FROM Student s")
    int getCountOfStudent();

    @Query(value = "SELECT AVG (s.age)FROM Student s")
    double getAverageAgeOfStudent();

    @Query(value = "SELECT s.* From student s ORDER BY s.id DESC LIMIT :count ",nativeQuery = true)
    List<Student> getLastStudent (@Param("count")int count);
}
