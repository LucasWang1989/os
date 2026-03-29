package nz.ac.sit.os.integration;

import nz.ac.sit.os.infrastructure.mybatis.mapper.MercOrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @program: os
 * @description: Integration test for auth-related operations
 * @author: Lucas Wang
 * @email: lucas.wang.1024@gmail.com
 * @date: 26/03/2026 22:14
 **/

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AuthApiIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    MercOrderMapper mercOrderMapper;

    @Test
    void should_login_success() throws Exception {
        MvcResult result = mockMvc.perform(post("/auth/login")
                        // Mock form submission
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "admin")
                        .param("password", "123456"))
                .andExpect(status().isOk()) // should return 200
                .andReturn();

    }

    @Test
    void encode() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String newHash = encoder.encode("123456");
        System.out.println("The encrypted text: " + newHash);
        System.out.println("Validate encrypted text: " + encoder.matches("123456", newHash));
    }

}