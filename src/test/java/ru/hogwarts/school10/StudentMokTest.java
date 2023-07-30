package ru.hogwarts.school10;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.hogwarts.school10.controller.StudentController;
import ru.hogwarts.school10.model.Student;
import ru.hogwarts.school10.services.StudentServices;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StudentMokTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    StudentServices studentServices;

    @MockBean
    StudentController studentController;

    ObjectMapper objectMapper;


    @Test
    void addTestStudent() throws Exception {
        Student student = new Student();

        student.setName("Harry Potter");
        student.setAge(25);

        when(studentServices.createStudent(student)).thenReturn(student);

        ResultActions resultActions =
                mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()));
    }


    @Test
    void getStudentTestId() throws Exception {

        Student student = new Student();
        Long id = 1L;
        String name = "Гермиона";
        int age = 25;

        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentServices.getStudentId(id)).thenReturn(student);

        ResultActions resultActions =
                mockMvc.perform(
                        get("/student/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student))
                );

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()));

    }

    @Test
    void getByAgeStudentTest() throws Exception {
        Student student1 = new Student(1L, "Tany", 25);
        Student student2 = new Student(2L, "Masha", 30);

        List<Student> listStudent = List.of(student1, student2);
        when(studentServices.getStudentAge(anyInt())).thenReturn(listStudent);
        JSONArray jsonA = new JSONArray();
        jsonA.put(student1);
        jsonA.put(student2);

        ResultActions resultActions=
        mockMvc.perform(get("/student/age/25")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(jsonA)));

      resultActions
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.id").value(student1.getId() ))
              .andExpect(jsonPath("$.name").value(student1.getName() ))
              .andExpect(jsonPath("$.age").value(student1.getAge() ))
              .andExpect(jsonPath("$.id").value(student2.getId() ))
              .andExpect(jsonPath("$.name").value(student2.getName() ))
              .andExpect(jsonPath("$.age").value(student2.getAge() ));

    }


}
