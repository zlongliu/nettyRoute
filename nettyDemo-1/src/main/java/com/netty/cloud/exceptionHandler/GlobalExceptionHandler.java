package com.netty.cloud.exceptionHandler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.netty.cloud.exception.ServiceException;
import com.netty.cloud.vo.JsonResultVo;
/**
 * 全局异常处理类
 * @author CYQ
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * 处理自定义异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ServiceException.class)
	public JsonResultVo doHandlerServiceException(RuntimeException e) {
		return JsonResultVo.fail();
	}
}
