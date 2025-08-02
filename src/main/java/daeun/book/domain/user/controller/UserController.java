package daeun.book.domain.user.controller;


import daeun.book.domain.user.dto.reqeust.LoginRequest;
import daeun.book.domain.user.dto.reqeust.SignupRequest;
import daeun.book.domain.user.dto.response.LoginResponse;
import daeun.book.domain.user.dto.response.SignupResponse;
import daeun.book.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignupResponse> signUp(@RequestBody SignupRequest request) {
        return ResponseEntity.ok(userService.signUp(request));
    }

    //TODO: 로그인 API 구현
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }
}
