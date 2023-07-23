package ru.hogwarts.school10;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school10.controller.StudentController;
import ru.hogwarts.school10.model.Student;
import ru.hogwarts.school10.repositories.StudentRepositoriy;
import ru.hogwarts.school10.services.StudentServices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StudentMok {

    @Autowired
    private MockMvc mockMvc;

    @MockBean //создаем моки репозиторий
    private StudentRepositoriy studentRepositoriy;



    @SpyBean //бин без изменения
    private StudentServices studentServices;

    @InjectMocks // инжектим моки в контроллер. Контроллер будет использовать объекты, помеченные анотацией.
    private StudentController studentController;

    public StudentMok(StudentController studentController) {
        this.studentController = studentController;
    }

    JSONObject studentObject = new JSONObject();

    @Test
    public void saveUserTest() throws Exception {
        Long id = 1L;
        String name = "Roman";
        int age = 25;

        //входные данные
        JSONObject userObject = new JSONObject();
        userObject.put("id", id);
        userObject.put("name", name);
        userObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepositoriy.save(any(Student.class))).thenReturn(student);
        when(studentRepositoriy.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + id)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));

    }

    @Test
    public void getFindAllTest() throws Exception {

       Collection<Student> mockList = Mockito.spy(new ArrayList<Student>());

        Long id = 10L;
        String name = "Nikita";
        int age = 35;

        //входные данные
        JSONObject userObject = new JSONObject();
        userObject.put("id", id);
        userObject.put("name", name);
        userObject.put("age", age);

        Student student = new Student();

        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepositoriy.findAll(any(Student.class))).thenReturn(mockList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/")
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }


}
