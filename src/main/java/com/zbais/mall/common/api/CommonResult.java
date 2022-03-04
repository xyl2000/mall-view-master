package com.zbais.mall.common.api;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Zbais
 * @Date: 2021/11/28/17:26
 * @Description:  通用的返回对象
 */

@Data
public class CommonResult<T> {
    private long code;
    private String message;
    private T data;

    public CommonResult() {
    }


    public CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    /**
     * * @param data
     * * @return CommonResult<T>
     * @Description:    成功返回数据
     */
    public static <T> CommonResult<T> success(T data){
        return new CommonResult<>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),data);
    }

    /**
     * * @param message  提示信息
     * @param data 获取数据
     * * @return CommonResult<T>
     * @Description:
     */
    public static <T> CommonResult<T> success(String message, T data){
        return new CommonResult <>(ResultCode.SUCCESS.getCode(), message, data);
    }
    /**
     * * @return CommonResult<T>
     * @Description: 错误返回结果
     */
    public static <T> CommonResult<T> failed(){
        return failed(ResultCode.FAILED);
    }
    /**
     * * @param message 错误提示信息
     * * @return CommonResult<T>
     * @Description: 错误返回结果
     */
    public static <T> CommonResult<T> failed(String message){
        return new CommonResult<>(ResultCode.FAILED.getCode(), message, null);
    }
    /**
     * * @param errorCode  错误码
     * * @return CommonResult<T>
     * @Description:        错误返回结果
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode){
        return new CommonResult<>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * * @param errorCode  错误码
     * @param message 错误提示信息
     * * @return CommonResult<T>
     * @Description: 错误返回结果
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode, String message){
        return new CommonResult<>(errorCode.getCode(), message, null);
    }

    /**
     *
     * * @return CommonResult<T>
     * @Description:    验证失败返回结果
     */
    public static <T> CommonResult<T> validateFailed(){
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * * @param message 验证失败提示信息
     * * @return CommonResult<T>
     * @Description:        验证失败返回结果
     */
    public static <T> CommonResult<T> validateFailed(String message){
        return new CommonResult<>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }
    
    /**
     * 
     * * @return CommonResult<T> 
     * @Description:        未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized(T date){
        return new CommonResult<>(ResultCode.UNAUTHORIZED.getCode(),ResultCode.UNAUTHORIZED.getMessage(), date);
    }

    /**
     * * @param date
     * * @return CommonResult<T>
     * @Description:   无相关权限返回结果
     */
    public static <T> CommonResult<T>  forbidden(T date){
        return new CommonResult<>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), date);
    }
    


}
