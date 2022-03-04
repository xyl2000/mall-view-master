package com.zbais.mall.common.api;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Zbais
 * @Date: 2021/11/28/17:18
 * @Description:
 */

public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token过期"),
    FORBIDDEN(403, "没有相关权限");

    private long code;
    private String message;

    ResultCode(long code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
