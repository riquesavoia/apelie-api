package com.apelie.apelieapi.controllers;

import com.apelie.apelieapi.controllers.dto.user.CreateUserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

/*
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")*/
class UserControllerIntegrationTest {
/*
    @Autowired
    private MockMvc mvc;

    @Test
    public void whenCreateValidUser_thenStatus201() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CreateUserDto user = new CreateUserDto();
        user.setEmail("test@test.com");
        user.setFullName("FullName Test");
        user.setPassword("testpassword123");

        mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user)))
                .andExpect(status().isCreated());

    }

    @Test
    public void whenCreateUserWithInvalidEmail_thenStatus400() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CreateUserDto user = new CreateUserDto();
        user.setEmail("test@");
        user.setFullName("FullName Test");
        user.setPassword("testpassword123");

        mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenCreateUserWithInvalidName_thenStatus400() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CreateUserDto user = new CreateUserDto();
        user.setEmail("test@test.com");
        user.setFullName("   ");
        user.setPassword("testpassword123");

        mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenCreateUserWithInvalidPassword_thenStatus400() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CreateUserDto user = new CreateUserDto();
        user.setEmail("test@test.com");
        user.setFullName("Fullname Test");
        user.setPassword("12345");

        mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }*/
}