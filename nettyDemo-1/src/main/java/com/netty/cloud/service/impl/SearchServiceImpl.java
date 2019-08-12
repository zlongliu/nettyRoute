package com.netty.cloud.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.netty.cloud.exception.ServiceException;
import com.netty.cloud.mapper.RtDownEquipmentMapper;
import com.netty.cloud.mapper.RtInfoMapper;
import com.netty.cloud.mapper.RtLoadMapper;
import com.netty.cloud.mapper.RtSpeedTestLogMapper;
import com.netty.cloud.pojo.RtDownEquipment;
import com.netty.cloud.pojo.RtInfo;
import com.netty.cloud.pojo.RtLoad;
import com.netty.cloud.pojo.RtSpeedTestLog;
import com.netty.cloud.service.SearchService;

import redis.clients.jedis.Jedis;
/**
 * 查询类请求处理类
 * @author CYQ
 *
 */
@Service
public class SearchServiceImpl implements SearchService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);
	@Autowired
	private Jedis jedis;
	@Autowired
	private RtDownEquipmentMapper rtDownEqpMapper;
	@Autowired
	private RtInfoMapper rtInfoMapper;
	@Autowired
	private RtLoadMapper rtLoadMapper;
	@Autowired
	private RtSpeedTestLogMapper rtSpeedTestMapper;
	@SuppressWarnings("deprecation")
	@Override
	public Object handlerSearch(Map<String, Object> params) {
		LOGGER.info("查询服务实现类");
		Map<String, String> param = getParameters(params);
		String typeName = param.get("typeName");
		String brdnetAcct = param.get("brdnetAcct");
		String mac = param.get("mac");
		Date orderDate = new Date();
		orderDate.setMinutes(orderDate.getMinutes()-2);		
		jedis.setex(brdnetAcct, 60, mac);		
		Object outputObject = getOutputObject(typeName, mac, orderDate);
		return outputObject;
	}
	/**
	 * 根据typeName，返回不同的Object
	 * @param typeName
	 * @return
	 */
	private Object getOutputObject(String typeName, String mac, Date orderDate) {
		Object result = null;
		switch (typeName) {
		case "1" : result = getRouteDetails(mac);break;
		case "3" : result = getSpeedTestResult(mac, orderDate);break;
		case "5" : result = getDownEqps(mac, orderDate);break;
		case "6" : result = getWanSpeed(mac, orderDate);break;
		case "7" : result = getRoutePerform(mac, orderDate);break;
		default : LOGGER.info("传入typeName值不在范围内");throw new ServiceException();
		}
		return result;
	}
	/**
	 * 查询路由器性能方法
	 * @param mac
	 * @param orderDate
	 * @return
	 */
	private Object getRoutePerform(String mac, Date orderDate) {
		LOGGER.info("查询路由器性能方法 start");
		QueryWrapper<RtLoad> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("rt_mac_addr", mac).gt("updateTime", orderDate);
		RtLoad rtLoad = rtLoadMapper.selectOne(queryWrapper);
		LOGGER.info("查询路由器性能方法 end");
		return rtLoad;
	}
	/**
	 * 查询wan口速率方法
	 * @param mac
	 * @param orderDate
	 * @return
	 */
	private Object getWanSpeed(String mac, Date orderDate) {
		LOGGER.info("查询wan口速率方法 start");
		QueryWrapper<RtLoad> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("rt_mac_addr", mac).gt("updateTime", orderDate);
		RtLoad rtLoad = rtLoadMapper.selectOne(queryWrapper);
		LOGGER.info("查询wan口速率方法 end");
		return rtLoad;
	}
	/**
	 * 查询下挂设备方法
	 * @param mac
	 * @param orderDate
	 * @return
	 */
	private Object getDownEqps(String mac, Date orderDate) {
		LOGGER.info("查询下挂设备方法 start");
		QueryWrapper<RtDownEquipment> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("rt_mac_addr", mac).gt("updateTime", orderDate);
		List<RtDownEquipment> rtDownEqpList = rtDownEqpMapper.selectList(queryWrapper);
		LOGGER.info("查询下挂设备方法 end");
		return rtDownEqpList;
	}
	/**
	 * 一键测试结果方法
	 * @param mac
	 * @param orderDate
	 * @return
	 */
	private Object getSpeedTestResult(String mac, Date orderDate) {
		LOGGER.info("查询一键测试结果方法 start");
		QueryWrapper<RtSpeedTestLog> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("rt_mac_addr", mac).gt("opt_time", orderDate);
		RtSpeedTestLog rtSpeedTest = rtSpeedTestMapper.selectOne(queryWrapper);
		LOGGER.info("查询一键测试结果方法 end");
		return rtSpeedTest;
	}
	/**
	 * 查看路由详情方法
	 * @param mac
	 * @return
	 */
	private Object getRouteDetails(String mac) {
		LOGGER.info("查询路由详情方法 start");
		QueryWrapper<RtInfo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("rt_mac_addr", mac);
		RtInfo rtInfo = rtInfoMapper.selectOne(queryWrapper);
		LOGGER.info("查询路由详情方法 end");
		return rtInfo;
	}
	/**
	 * 根据指定参数值查询路由器详情
	 * @param paramName
	 * @param paramValue
	 * @return
	 */
	private RtInfo getRouteDetails(String paramName, String paramValue) {
		QueryWrapper<RtInfo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("broadband_acc", paramValue);
		RtInfo infoDB = rtInfoMapper.selectOne(queryWrapper);
		return infoDB;
	}
	/**
	 * 封装参数的方法
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private Map<String, String> getParameters(Map<String, Object> params){
		Map<String, String> param = (Map<String, String>) params.get("params");
		String typeName = (String) param.get("typeName");
		String brdnetAcct = (String) param.get("brdnetAcct");
		if (StringUtils.isEmpty(typeName)||StringUtils.isEmpty(brdnetAcct)) {
			LOGGER.info("brdnetAcct和typeName值不能为空");
			throw new ServiceException();
		}
		String mac = jedis.get(brdnetAcct);	
		if (StringUtils.isEmpty(mac)) {
			RtInfo infoDB = getRouteDetails("broadband_acc", brdnetAcct);
			if (infoDB == null) {
				LOGGER.info("不支持哨兵服务");
				throw new ServiceException();
			}
			mac = infoDB.getRtMacAddr();
		}			
		param.put("mac", mac);
		return param;
	}
}
