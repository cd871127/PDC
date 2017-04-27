package com.anthony.config;

/**
 * Created by CHENDONG239 on 2017-04-27.
 */
public enum ErrorCode {
    USER_NOT_LOGIN("0001", "用户未登录"),
    USER_NOT_FOUND("0002", "用户不存在");

    private String ErrorName;
    private String ErrorCode;

    private ErrorCode(String ErrorCode, String ErrorName) {
        this.ErrorName = ErrorName;
        this.ErrorCode = ErrorCode;
    }

    public String getErrorName() {
        return ErrorName;
    }


    public String getErrorCode() {
        return ErrorCode;
    }


}
