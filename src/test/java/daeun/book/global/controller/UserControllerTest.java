package daeun.book.global.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import daeun.book.domain.user.dto.reqeust.LoginRequest;
import daeun.book.domain.user.dto.reqeust.SignupRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType; // 수정된 import
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath; // 수정된 import
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 테스트")
    void signup_success() throws Exception {
        // given: 테스트를 위한 조건 설정
        // DTO 생성자 순서가 (email, name, password)로 가정합니다. 본인 코드에 맞게 확인하세요.
        SignupRequest request = new SignupRequest("test@gmail.com", "testuser", "password123");
        String jsonRequest = objectMapper.writeValueAsString(request);

        // when: 실제 API를 호출하는 부분
        // then: API 호출 및 결과 검증
        mockMvc.perform(post("/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON) // 올바른 위치로 수정
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists());
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    void login_success() throws Exception {
        // given: 먼저 테스트용 사용자를 회원가입 시킵니다.
        // DTO 생성자 순서: (email, name, password)
        SignupRequest signupRequest = new SignupRequest("test@email.com", "password123", "testuser");
        mockMvc.perform(post("/auth/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signupRequest)));

        // 이제 로그인 요청을 준비합니다.
        LoginRequest loginRequest = new LoginRequest("test@email.com", "password123");
        String jsonRequest = objectMapper.writeValueAsString(loginRequest);

        // when & then
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andExpect(jsonPath("$.name").value("testuser"));
    }

    @Test
    @DisplayName("로그인 실패 테스트 - 잘못된 비밀번호")
    void login_fail_wrong_password() throws Exception {
        // given: 먼저 테스트용 사용자를 회원가입 시킵니다.
        SignupRequest signupRequest = new SignupRequest("test@email.com", "password123", "testuser");
        mockMvc.perform(post("/auth/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signupRequest)));

        // 비밀번호를 틀리게 해서 로그인 요청을 준비합니다.
        LoginRequest loginRequest = new LoginRequest("test@email.com", "wrong_password");
        String jsonRequest = objectMapper.writeValueAsString(loginRequest);

        // when & then
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isInternalServerError());
    }
}