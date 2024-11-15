package com.kusd.KUmap.global.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    //Field
    FIELD_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 진출분야를 찾을 수 없습니다."),

    //CourseDetails
    Course_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 강좌를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류, 관리자에게 문의하세요"),
    NOT_FOUND_COMPETITION_RATE(HttpStatus.NOT_FOUND, "해당 학수번호의 경쟁률을 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
