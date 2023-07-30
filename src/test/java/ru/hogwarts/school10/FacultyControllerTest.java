package ru.hogwarts.school10;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hogwarts.school10.controller.FacultyController;
import ru.hogwarts.school10.model.Faculty;
import ru.hogwarts.school10.repositories.FacultyRepositoriy;
import ru.hogwarts.school10.repositories.StudentRepositoriy;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@WebMvcTest(controllers = FacultyController.class)
public class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    FacultyRepositoriy facultyRepositoriy;

    @MockBean
    StudentRepositoriy studentRepositoriy;

    @Autowired
    ObjectMapper objectMapper;

    private final Faker faker = new Faker();

    @Test
    void createTest() throws Exception {
        Faculty facultyGeneraty = generate();
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName(facultyGeneraty.getName());
        faculty.setColor(facultyGeneraty.getColor());

        when(facultyRepositoriy.save(any())).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders.post("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(faculty)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    private Faculty generate() {
        Faculty faculty = new Faculty();
        faculty.setName(String.valueOf(faker.harryPotter()));
        faculty.setColor(faker.color().name());
        return faculty;
    }

}
