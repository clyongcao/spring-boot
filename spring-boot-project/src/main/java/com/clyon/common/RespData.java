package com.clyon.common;

import java.io.Serializable;

import com.clyon.emus.StatusCode;

/**
 * @author caoxuyang
 *
 * 统一返回结果类
 */
public class RespData<T> implements Serializable {

	/**
	 * 状态码：1、成功 其他为失败
	 */
	public int code;

	/**
	 * 成功为sucess，其他为失败原因
	 */
	public String message;

	/**
	 * 返回成功时为结果集 返回失败时不显示
	 */
	public T data;

	public RespData() {
	}

	public RespData(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public RespData(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public static <T> RespData<T> success() {
		return new RespData<T>(StatusCode.CODE_200.value(), StatusCode.CODE_200.remark());
	}

	public static <T> RespData<T> success(StatusCode StatusCode) {
		return new RespData<T>(StatusCode.value(), StatusCode.remark());
	}

	public static <T> RespData<T> fail(StatusCode StatusCode) {
		return new RespData<T>(StatusCode.value(), StatusCode.remark());

	}

	public static <T> RespData<T> fail() {
		return new RespData<T>(StatusCode.CODE_300.value(), StatusCode.CODE_300.remark());
	}

	public static <T> RespData<T> fail(String message) {
		return new RespData<T>(StatusCode.CODE_300.value(), message);
	}

	public static <T> RespData<T> success(T data) {
		return new RespData<T>(StatusCode.CODE_200.value(), StatusCode.CODE_200.remark(),data);

	}

	public static <T> RespData<T> success(T data,String message) {
		return new RespData<T>(StatusCode.CODE_200.value(), message,data);

	}

	public RespData CUSTOM(StatusCode statusCode) {
		this.code = statusCode.value();
		this.message = statusCode.remark();
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
    public String toString() {
        return "RespData{" +
                "code=" + code +
                ", msg='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}
