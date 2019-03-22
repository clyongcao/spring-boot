package com.clyon.web;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.alibaba.fastjson.JSON;
import com.clyon.common.RespData;
import com.clyon.emus.StatusCode;
import com.clyon.exception.ServiceException;

/**
 * <一句话功能简述>  统一捕获异常处理
 *
 * @author caoxuyang
 */
@ControllerAdvice
public class ExceptionTranslator {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionTranslator.class);

    /**
     * 捕获异常
     *
     * @param ex
     * @param request
     * @return
     */
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespData<Object>> processException(Exception ex, HttpServletRequest request)
            throws Exception {
        logger.error("", ex);
        logger.error("request url:{}", request.getRequestURI());
        logger.error("request content:{}", JSON.toJSON(request.getParameterMap()));
        
        return ResponseEntity.ok().body(new RespData(StatusCode.CODE_500.value(), StatusCode.CODE_500.remark()));

    }
    
	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<RespData<Object>> processException(ServiceException ex, HttpServletRequest request)
			throws Exception {
		logger.error("", ex);
		logger.error("request url:{}", request.getRequestURI());
		logger.error("request content:{}", JSON.toJSON(request.getParameterMap()));

		return ResponseEntity.ok().body(new RespData(ex.getCode(), ex.getMsg()));

	}
    
}