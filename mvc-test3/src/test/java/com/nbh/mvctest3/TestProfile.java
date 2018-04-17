package com.nbh.mvctest3;


import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)

@WebMvcTest(includeFilters = @ComponentScan.Filter(classes = EnableWebSecurity.class))
public class TestProfile {

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser("ringo123")
    public void shouldFindGreetings() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/greetings"))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(value = "ringo", password = "fries")
    public void shouldGetImportantInformation() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/important/stuff"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("user123")
    public void shouldFailGetImportantInformation() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/important/stuff"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser("nothing here")
    public void shouldGetImportantData()throws Exception{
        mockMvc.
                perform(MockMvcRequestBuilders.get("/data/view/Nelly?age=23&date=november23"))
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", CoreMatchers.is("Nelly")))
        .andExpect(jsonPath("$.age", CoreMatchers.is("23")));

        /*                .andExpect(jsonPath("$.name", is(courseName)))
                .andExpect(jsonPath("$.id", is(1)));*/
    }
}