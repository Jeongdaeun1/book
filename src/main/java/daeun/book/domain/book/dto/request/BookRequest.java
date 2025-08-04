package daeun.book.domain.book.dto.request;

import daeun.book.domain.book.enums.ReadStatus;

public record BookRequest (
        String name,
        String author,
        String publisher,
        ReadStatus status
){

}
