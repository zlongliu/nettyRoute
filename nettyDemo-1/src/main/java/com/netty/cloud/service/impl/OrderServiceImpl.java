package com.netty.cloud.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.netty.cloud.exception.ServiceException;
import com.netty.cloud.mapper.RtInfoMapper;
import com.netty.cloud.pojo.RtInfo;
import com.netty.cloud.service.OrderService;
import com.netty.cloud.vo.CloudSendVo;
import com.netty.util.JsonConvert;

import redis.clients.jedis.Jedis;
/**
 * 指令操作处理类
 * @author CYQ
 *
 */
@Service
public class OrderServiceImpl implements OrderService{
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
	@Autowired
	private Jedis jedis;
	@Autowired
	private RtInfoMapper rtInfoMapper;
	@Override
	public void handlerOrder(Map<String, Object> params) {
		LOGGER.info("指令操作");
		Map<String, Object> param = getParameters(params);
		String brdnetAcct = (String) param.get("brdnetAcct");
		String mac = (String) param.get("mac");
		jedis.setex(brdnetAcct, 60, mac);
		String value = jedis.get(mac);
		if (StringUtils.isEmpty(value)) {
			LOGGER.info("不支持哨兵服务，可能是路由器没有启动");
			throw new ServiceException();
		}
		String[] hostAndId = value.split("@");
		String hostName = hostAndId[0];
		String channelId = hostAndId[1];
		Long timeStamp = System.currentTimeMillis();
		String msgCode = (String) param.get("msgCode");
		Object content = param.get("content");
		CloudSendVo cloud2Client = new CloudSendVo();
		cloud2Client.setChannelId(channelId).setTimeStamp(timeStamp).setMsgCode(msgCode).setContent(content);
		String message = null;
		try {
			message = JsonConvert.Object2Json(cloud2Client);
		} catch (Exception e) {
			LOGGER.info("格式转换异常", e);
			throw new ServiceException("格式转换异常");
		}
		jedis.lpush(hostName, message);
	}
	/**
	 * 封装参数
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> getParameters(Map<String, Object> params){
		Map<String, Object> param = (Map<String, Object>) params.get("params");
		String typeName = (String) param.get("typeName");
		String brdnetAcct = (String) param.get("brdnetAcct");
		if (StringUtils.isEmpty(typeName)||StringUtils.isEmpty(brdnetAcct)) {
			throw new ServiceException("brdnetAcct和typeName值不能为空");
		}
		String mac = jedis.get(brdnetAcct);
		if (StringUtils.isEmpty(mac)) {
			QueryWrapper<RtInfo> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("broadband_acc", brdnetAcct);
			RtInfo infoDB = rtInfoMapper.selectOne(queryWrapper);
			if (infoDB == null) {
				LOGGER.info("不支持哨兵服务，可能是没有哨兵插件");
				throw new ServiceException();
			}
			mac = infoDB.getRtMacAddr();
		}
		String msgCode = getMsgCode(typeName);
		param.put("msgCode", msgCode);
		param.put("mac", mac);
		Object content = getContent(param);
		param.put("content", content);
		return param;
	}
	/**
	 * 获取content
	 * @param param
	 * @return
	 */
	private Object getContent(Map<String, Object> param) {
		Object content = null;
		String msgCode = (String) param.get("msgCode");
		if ("updateSSIDPassword".equals(msgCode)) {
			content = param.get("ssidPwd");
			if (content == null) {
				throw new ServiceException("请传入要修改的密码");
			}
		}
		if ("delDoubtfulEquipment".equals(msgCode)) {
			content = param.get("eqpMacAddrs");
			if (content == null) {
				throw new ServiceException("请传入要剔除设备的mac地址");
			}
		}
		return content;
	}
	/**
	 * 获取msgCode
	 * @param typeName
	 * @return
	 */
	private String getMsgCode(String typeName) {
		String msgCode = null;
		switch (typeName) {
		case "1" : msgCode = "restart";break;
		case "2" : msgCode = "updateSSIDPassword";break;
		case "3" : msgCode = "testSpeed";break;
		case "4" : msgCode = "delDoubtfulEquipment";break;
		case "5" : msgCode = "selectConnectEquipmentResult";break;
		case "6" : msgCode = "selectWAN";break;
		case "7" : msgCode = "routesPerformance";break;
		default : LOGGER.info("传入typeName值不在范围内");throw new ServiceException();
		}
		return msgCode;
	}
	
}
