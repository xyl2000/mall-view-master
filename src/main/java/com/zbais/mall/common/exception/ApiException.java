package com.zbais.mall.common.exception;

import com.zbais.mall.common.api.IErrorCode;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * 自定义API异常
 * @Author: Zbais
 * @Date: 2021/11/28/19:39
 * @Description:
 */

@Data
public class ApiException extends RuntimeException{
    private IErrorCode errorCode;

    ApiException(IErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message){
        super(message);
    }
    public ApiException(Throwable cause){
        super(cause);
    }
    public ApiException(String message, Throwable cause){
        super(message, cause);
    }

}
