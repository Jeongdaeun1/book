package daeun.book.domain.user.dto.response;

public record LoginResponse (
    String accessToken,
    String name
) {
}
