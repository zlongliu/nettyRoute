package com.netty.cloud.service;

import java.util.Map;
/**
 * 查询类请求统一处理接口
 * @author CYQ
 *
 */
public interface SearchService {
	/**
	 * 统一查询方法
	 * @param params
	 * @return
	 */
	Object handlerSearch(Map<String, Object> params);

}
