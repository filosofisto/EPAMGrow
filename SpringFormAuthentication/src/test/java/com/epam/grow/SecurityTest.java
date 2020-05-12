package com.epam.grow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityTest {

    private static final String URL_NO_AUTH = "/";
    private static final String URL_AUTH = "/hello";
    private static final String URL_LOGIN = "/login";
    private static final String USERNAME = "admin";
    private static final String CORRECT_PASSWORD = "123456";
    private static final String INCORRECT_PASSWORD = "123456x";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void unprotectedRequestWithoutUserShouldReceiveStatus200() throws Exception {
        this.mockMvc.perform(get(URL_NO_AUTH))
                .andExpect(status().isOk());
    }

    @Test
    void unprotectedRequestWithUserShouldReceiveStatus200() throws Exception {
        this.mockMvc.perform(
                get(URL_NO_AUTH)
                .with(user(USERNAME).password(CORRECT_PASSWORD))
                ).andExpect(status().isOk());
    }

    @Test
    void protectedRequestWithoutUserShouldReceiveStatus302AndHeaderLocation() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get(URL_AUTH))
                .andExpect(status().isMovedTemporarily())
                .andReturn();
        String location = mvcResult.getResponse().getHeader("location");

        Assertions.assertTrue(location != null && location.contains("login"));
    }

    @Test
    void protectedRequestWithUserShouldReceiveStatus200() throws Exception {
        mockMvc.perform(
                get(URL_AUTH)
                .with(user(USERNAME).password(CORRECT_PASSWORD))
        ).andExpect(status().isOk());
    }

    @Test
    void protectedWithWrongUserShouldReceiveStatus403() throws Exception {
        mockMvc.perform(
                post(URL_LOGIN).with(user(USERNAME).password(INCORRECT_PASSWORD))
        ).andExpect(status().isForbidden());
    }
}
