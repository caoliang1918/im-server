package org.zhongweixian.live.exception;

import java.text.MessageFormat;

/**
 *
 */
public enum ErrorCode {
    /**
     * 用户模块-注册登录
     */
    USER_REGISTER_ERROR(71001, "用户注册失败"),
    USER_LOGIN_RANDOM_NOT_NULL(71002, "验证码不能为空"),
    USER_LOGIN_ERROR(71003, "用户名或密码错误"),
    USER_LOGIN_SUCCESS(71004, "登录成功"),
    USER_CHECK_EXIST_USERNAME_MOBILE_EMAIL_NULL(71005, "username、mobile和email不能同时为空"),
    USER_REGSITER_MOBILE_CODE_NULL(71006, "手机验证码错误"),
    USER_REGSITER_RENDOM_CODE_NULL(71007, "验证码错误"),
    USER_REGSITER_USERNAME_EXIST(71008, "用户名已存在"),
    USER_REGSITER_MOBILE_EXIST(71009, "手机号已存在"),
    USER_REGSITER_EMAIL_EXIST(71010, "邮箱已存在"),
    USER_REGSITER_SUCCESS(71011, "注册成功"),
    USR_LOGIN_TIME_OUT(71012, "登录超时或在其他地方登录"),
    USER_LOGIN_PWD_ERROR(71013, "原密码错误"),
    USER_AUTH_ERROR(71014, "Authorization令牌错误"),
    USER_MOBILE_NULL(71015, "用户未绑定手机号"),
    USER_OTP_SECRET_ERROR(710016 ,"otp认证超时"),
    USER_OTP_VALIDATE_ERROR(710017 ,"otp认证错误"),
    USER_OTP_NOT_ABLE(710018 ,"未绑定google身份验证器"),
    USER_EMAIL_ERROR(710019 ,"邮箱格式错误"),
    USER_REGSITER_EMAIL_CODE_NULL(710020 ,"邮箱验证码错误"),

    /**
     * 公共异常
     */
    SYS_SQL_EXCPETION(79001, "数据库异常"),
    SYS_JSON_ERROR(79002, "JSON转换异常"),
    SYS_SERVER_ERROR(79003, "请检查请求或者服务配置是否正确后再次尝试"),
    SYS_NUMBER_FORMAT_ERROR(79004, "数字转换异常"),
    SYS_CLASSCAST_ERROR(79005, "类型转换异常"),
    SYS_MEDIATYPE_NOT_SUPPORT(79006, "ContentType : {0} 类型错误"),
    SYS_NULLPOINTER_ERROR(79007, "空指针异常"),
    SYS_PARAMETER_ERROR(79008, "请求参数错误"),
    SYS_METHOD_NOT_SUPPORT(79009, "请求方法错误,不支持 {0} 请求"),
    SYS_ARGUMENT_TYPE__ERROR(79011, "参数{0}类型错误"),
    SYS_REPEAT_SUBMIT_EXCEPTION(79012, "请勿重复操作"),
    BEAN_EMPTY_PROPERTY_ERROR(79013, "参数{0}绑定错误"),
    SYS_PARAMETERS_EMPTY_ERROR(79014, "参数{0}不能为空"),
    SYS_NO_HANDLER_FOUND_ERROR(79015, "资源{0}未找到"),
    SYS_OPETION_ERROR(79015, "操作异常");


    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage(Object... param) {
        if (param.length == 0) {
            return message;
        }
        return MessageFormat.format(message, param);
    }

    public static ErrorCode getByCode(int code) {
        ErrorCode[] values = ErrorCode.values();
        for (ErrorCode value : values) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

}