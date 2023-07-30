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
import ru.hogwarts.school10.controller.FacultyController;
import ru.hogwarts.school10.model.Faculty;
import ru.hogwarts.school10.services.FacultyServices;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FacultyMokTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    FacultyServices facultyServices;

    @MockBean
    FacultyController facultyController;

    ObjectMapper objectMapper;


    @Test
    void getFacultyIdTest () throws Exception{
        Faculty faculty = new Faculty();
        faculty.setColor("grey");
        faculty.setName("Googf");

        when(facultyServices.getFaculty(1)).thenReturn(faculty);

        ResultActions resultActions =
                mockMvc.perform(get("faculty/1") //Он отправляет запрос get с некоторыми JSON данными и заголовком авторизации в faculty/1
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(faculty)));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(faculty.getId()))
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()));

    }

    @Test
    void createFacultyTest () throws Exception{
        Faculty faculty = new Faculty();

        faculty.setColor("blue");
        faculty.setName("Lookiy");

        when(facultyServices.createFaculty(faculty)).thenReturn(faculty);

        ResultActions resultActions  =
                mockMvc.perform(post("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(faculty)));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.age").value(faculty.getColor()));
    }





 /*   @GetMapping("{color}")

    public ResponseEntity<Collection<Faculty>> findFacultyColor(@PathVariable String color) {
        return ResponseEntity.ok(facultyServices.findColorFaculty(color));
    }*/

    @Test
    void getFacultyColorTest() throws Exception {

        Faculty f1 = new Faculty(1L, "Tany", "grey");
        Faculty f2 = new Faculty(2L, "Masha", "blue");

        List<Faculty> listFaculty = List.of(f1, f2);

        when(facultyServices.findColorFaculty(anyString())).thenReturn(listFaculty);
        JSONArray jsonA = new JSONArray();
        jsonA.put(f1);
        jsonA.put(f2);

        ResultActions resultActions=
                mockMvc.perform(get("/faculty/color/blue")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(jsonA)));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(f1.getId() ))
                .andExpect(jsonPath("$.name").value(f1.getName() ))
                .andExpect(jsonPath("$.age").value(f1.getColor() ))
                .andExpect(jsonPath("$.id").value(f2.getId() ))
                .andExpect(jsonPath("$.name").value(f2.getName() ))
                .andExpect(jsonPath("$.age").value(f2.getColor() ));

    }

}
