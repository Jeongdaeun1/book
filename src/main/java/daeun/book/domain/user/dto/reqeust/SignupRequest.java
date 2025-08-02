package daeun.book.domain.user.dto.reqeust;

public record SignupRequest(
        String email,
        String password,
        String name
) {
}
