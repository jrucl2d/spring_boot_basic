package com.spring.security.yu;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UserAccessTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    UserDetails testUSer() {
        return User.builder()
                .username("yu").password(passwordEncoder.encode("1234")).roles("USER")
                .build();
    }
    UserDetails testAdmin() {
        return User.builder()
                .username("admin").password(passwordEncoder.encode("1234")).roles("ADMIN")
                .build();
    }

    @DisplayName("user로 user 페이지를 접근할 수 있다.")
    @Test
//    @WithMockUser(username = "yu", roles = {"USER"}) // USER role을 가진 가상의 유저
    void test_user_access_userpage() throws Exception {
        String response = mockMvc.perform(get("/user").with(user(testUSer())))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        SecurityMessage message = objectMapper.readValue(response, SecurityMessage.class);
        assertEquals("user page", message.getMessage());
    }

    @DisplayName("user로 admin 페이지를 접근할 수 없다.")
    @Test
    void test_user_cannot_access_adminpage() throws Exception {
        mockMvc.perform(get("/admin").with(user(testUSer())))
                .andExpect(status().is4xxClientError());
    }

    @DisplayName("admin이 user 페이지와 admin 페이지를 접근할 수 있다.")
    @Test
    void test_user_can_access_userpage_and_adminpage() throws Exception {
        String response = mockMvc.perform(get("/user").with(user(testAdmin())))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        SecurityMessage message = objectMapper.readValue(response, SecurityMessage.class);
        assertEquals("user page", message.getMessage());

        response = mockMvc.perform(get("/admin").with(user(testAdmin())))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        message = objectMapper.readValue(response, SecurityMessage.class);
        assertEquals("admin page", message.getMessage());
    }

    @DisplayName("login 페이지는 아무나 접근할 수 있다.")
    @Test
    void test_anonymous_can_access_loginpage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @DisplayName("어느 페이지에도 로그인 하지 않은 사람은 접근할 수 없다.")
    @Test
     void test_anonymous_cannot_access_everywhere() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection()); // 302 redirect to /login
        mockMvc.perform(get("/user"))
                .andExpect(status().is3xxRedirection()); // 302 redirect to /login
        mockMvc.perform(get("/admin"))
                .andExpect(status().is3xxRedirection()); // 302 redirect to /login
    }
}
