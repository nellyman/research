package com.nbh.springmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CoursesController.class)
public class CoursesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CourseRepo courseRepo;

    String courseName = "RE";

    @Test
    public void shouldAddCourseIntoSystem() throws Exception {

        Course course = new Course(1L,courseName);
        Mockito.when(courseRepo.save(Mockito.any(Course.class))).thenReturn(course);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/courses")
                        .content(asJsonString(course))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void GetTheCourse()throws Exception{

        Course course = new Course(1L,courseName);
        Mockito.when(courseRepo.findByName(courseName)).thenReturn(course);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/courses/"+courseName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(courseName)))
                .andExpect(jsonPath("$.id", is(1)));

        Mockito
                .verify(courseRepo,
                        Mockito.times(1))
                .findByName(courseName);
    }


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}