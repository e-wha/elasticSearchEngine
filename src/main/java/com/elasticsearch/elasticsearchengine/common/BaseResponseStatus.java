package com.elasticsearch.elasticsearchengine.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@RequiredArgsConstructor
public enum BaseResponseStatus {

    /**
     * 200: 요청 성공
     **/
    SUCCESS(HttpStatus.OK, true, 200, "요청에 성공했습니다."),
    NONE_DATA(HttpStatus.OK, true, 200, "조회할 게시물이 없습니다."),

    /**
     * 400 : security 에러
     */
    WRONG_PAGE_NUM_MIN(HttpStatus.BAD_REQUEST, false, 400, "잘못된 페이지 번호입니다. (최소 1 이상)"),
    WRONG_PARAM(HttpStatus.BAD_REQUEST, false, 400, "잘못된 요청 (필수 값 누락 또는 잘못된 입력)"),
    WRONG_PAGE_NUM_MAX(HttpStatus.BAD_REQUEST, false, 400, "잘못된 size 값입니다. (1~100)"),
    /**
     * 500 : security 에러
     */
    WRONG_SERVER(HttpStatus.INTERNAL_SERVER_ERROR, false, 500, "서버 내부 오류가 발생"),

    /**
     * 900: 기타 에러
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 900, "Internal server error"),
    NO_EXIST_IMAGE(HttpStatus.NOT_FOUND, false, 901, "존재하지 않는 이미지 입니다");

    private final HttpStatusCode httpStatusCode;
    private final boolean isSuccess;
    private final int code;
    private final String message;

}