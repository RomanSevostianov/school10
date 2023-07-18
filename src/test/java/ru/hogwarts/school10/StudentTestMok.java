package ru.hogwarts.school10;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school10.controller.AvatarController;
import ru.hogwarts.school10.controller.FacultyController;
import ru.hogwarts.school10.controller.StudentController;
import ru.hogwarts.school10.model.Student;
import ru.hogwarts.school10.repositories.AvatarRepositoriy;
import ru.hogwarts.school10.repositories.FacultyRepositoriy;
import ru.hogwarts.school10.repositories.StudentRepositoriy;
import ru.hogwarts.school10.services.AvatarServices;
import ru.hogwarts.school10.services.FacultyServices;
import ru.hogwarts.school10.services.StudentServices;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StudentTestMok {

    @Autowired
    private MockMvc mockMvc;


    @MockBean //создаем моки репозиторий
    private StudentRepositoriy studentRepositoriy;
    @MockBean
    private FacultyRepositoriy facultyRepositoriy;
    @MockBean
    private AvatarRepositoriy avatarRepositoriy;

    @SpyBean //бин без изменения
    private StudentServices studentServices;
    @SpyBean
    private FacultyServices facultyServices;
    @SpyBean
    private AvatarServices avatarServices;

    @InjectMocks // инжектим моки в контроллер
    private StudentController studentController;
    @InjectMocks
    private FacultyController facultyController;
    @InjectMocks
    private AvatarController avatarController;


    @Test
    void saveStudentTest() throws Exception {
        JSONObject studentObject = new JSONObject();

        final long id = 1;
        final String name = "Roman";
        final Integer age = 25;

        //данные которые должны отправлять
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student student = new Student();
        //данные которые должны вернутсья
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepositoriy.save(any(Student.class))).thenReturn(student);
        when(studentRepositoriy.findById(any(Long.class))).thenReturn(Optional.of(student));


        mockMvc.perform(MockMvcRequestBuilders
                        .post("/studens")
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


}
