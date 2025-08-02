package daeun.book.domain.user.service;

import daeun.book.domain.user.dto.reqeust.SignupRequest;
import daeun.book.domain.user.dto.response.SignupResponse;
import daeun.book.domain.user.entity.User;
import daeun.book.domain.user.repository.UserRepository;
import daeun.book.global.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    @Transactional
    public SignupResponse signUp(SignupRequest request) {
        String email = request.email();
        String name = request.name();
        String password = request.password();

        User user = User.builder()
                .email(email)
                .name(name)
                .password(password)
                .build();

        userRepository.save(user);

        String accessToken = tokenProvider.generateToken(user, Duration.ofDays(1));

        return new SignupResponse(accessToken);
    }

    //TODO: 로그인 구현

}