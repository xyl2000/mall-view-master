package com.zbais.mall.common.exception;

import com.zbais.mall.common.api.IErrorCode;

/**
 * Created with IntelliJ IDEA.
 * 断言：用于处理各种处理类
 * @Author: Zbais
 * @Date: 2021/11/28/19:47
 * @Description:
 */

public class Asserts {
    public static ApiException fail(String message){
        return new ApiException(message);
    }
    public static ApiException fail(IErrorCode errorCode){
        return new ApiException(errorCode);
    }
}
