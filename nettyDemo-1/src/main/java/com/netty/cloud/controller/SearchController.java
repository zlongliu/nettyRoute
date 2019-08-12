package com.netty.cloud.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netty.cloud.exception.ServiceException;
import com.netty.cloud.service.SearchService;
import com.netty.cloud.vo.JsonResultVo;
import com.netty.util.JsonConvert;
/**
 * 查询类请求处理类
 * @author CYQ
 *
 */
@RestController
@RequestMapping("/search")
public class SearchController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);
	@Autowired
	private SearchService searchService;
	@SuppressWarnings("unchecked")
	@RequestMapping("/")
	public JsonResultVo doHandlerReq(String inputObject) {
		if (inputObject == null) {
			throw new ServiceException("参数不能为空");
		}
		Map<String, Object> params = new HashMap<>();
		try {
			params = JsonConvert.Json2Object(inputObject, params.getClass());
		} catch (IOException e) {
			LOGGER.info("转化异常", e);
			throw new ServiceException();
		}
		Object outputObject = searchService.handlerSearch(params);
		return JsonResultVo.success(outputObject);
	}
}
