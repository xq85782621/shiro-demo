package com.meizhi.common.code;

/**
 *      公共的返回码,
 *      返回码code：
 *      成功：10000
 *      失败：10001
 *      未登录：10002
 *      未授权：10003
 *      抛出异常：99999
 */
public enum CommonCode implements ResultCode {

    SUCCESS(true,10000,"操作成功！"),
    LOGIN_SUCCESS(true,10000,"登陆成功"),
    //---系统错误返回码-----
    FAIL(false,10001,"操作失败"),
    LOGIN_FAIL(false,10000,"登录失败,请检查用户名密码"),

    UN_AUTHENTICATED(false,10002,"您还未登录"),
    SESSION_INVALID(false,10002,"会话已过期,请从新登录"),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！"),

    SQL_ERROR(false,50000,"SQL执行异常,请联系管理员"),
    SESSION_NEED_NOT_REFRESH(false,99999,"session 暂不能刷新");


    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    CommonCode(boolean success,int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }



    public boolean success() {
        return success;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

}
