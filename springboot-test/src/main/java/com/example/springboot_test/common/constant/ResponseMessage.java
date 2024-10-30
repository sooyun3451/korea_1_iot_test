package com.example.springboot_test.common.constant;

public class ResponseMessage {

    // 성공 및 일반 메시지
    public static final String SUCCESS = "Success"; // 성공 시 반환 메시지
    public static final String VALIDATION_FAIL = "Validation failed."; // 유효성 검사 실패 시 반환 메시지
    public static final String DATABASE_ERROR = "Database error."; // 데이터베이스 에러 시 반환 메시지

    // 존재 여부 관련 메시지
    public static final String EXIST_DATA = "Data already exists."; // 데이터가 이미 존재할 때 반환 메시지
    public static final String NOT_EXIST_DATA = "Data does not exist."; // 데이터가 존재하지 않을 때 반환 메시지

    // 존재 여부 관련 메시지
    public static final String NOT_EXIST_POST = "Book does not exist."; // 게시글이 존재하지 않을 때 반환 메시지
}
