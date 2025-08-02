package daeun.book.domain.user.dto.reqeust;

public record LoginRequest(
        String email,
        String password
) {
}
