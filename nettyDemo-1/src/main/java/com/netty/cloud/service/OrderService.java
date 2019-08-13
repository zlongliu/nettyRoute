package com.netty.cloud.service;

import java.util.Map;
/**
 * 指令类请求统一处理接口
 * @author CYQ
 *
 */
public interface OrderService {
	/**
	 * 统一发送指令方法
	 * @param params
	 */
	void handlerOrder(Map<String, Object> params);

}
