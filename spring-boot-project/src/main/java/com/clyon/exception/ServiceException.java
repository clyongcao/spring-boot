package com.clyon.exception;

import com.clyon.emus.StatusCode;

/**
 * @author caoxuyang
 */
public class ServiceException extends Exception {

    private Object data;

    private int code;

    private String msg;

    private String log;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public ServiceException() {
    }

    public ServiceException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ServiceException(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ServiceException(String log, int code, String msg, Object data) {
        super(log);
        this.log = log;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ServiceException(String log, StatusCode statusCode) {
        super(log);
        this.code = statusCode.value();
        this.msg = statusCode.remark();
        this.log = log;
    }

    public ServiceException(StatusCode statusCode) {

        this.code = statusCode.value();
        this.msg = statusCode.remark();
    }

    public ServiceException(String log, int code, String msg) {
        super(log);
        this.code = code;
        this.msg = msg;
        this.log = log;
    }

    public ServiceException(String log, Throwable cause, int code, String msg) {
        super(log, cause);
        this.code = code;
        this.msg = msg;
        this.log = log;
    }

    public ServiceException(Throwable cause, int code, String msg) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    public ServiceException(String log, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String msg) {
        super(log, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.msg = msg;
        this.log = log;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", log='" + log + '\'' +
                '}';
    }
}
