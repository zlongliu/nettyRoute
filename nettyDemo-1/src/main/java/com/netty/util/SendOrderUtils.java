package com.netty.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netty.cloud.exception.ServiceException;
import com.netty.cloud.vo.CloudSendVo;
import com.netty.server.NettyServerSocketReceive;

import io.netty.channel.Channel;

/**
 * 向通道发送消息的工具类
 * @author CYQ
 *
 */
public abstract class SendOrderUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(NettyServerSocketReceive.class);
	/**
	 * 向通道发送指定格式的消息
	 * @param message
	 */
	public static void send(String message) {
		try {
			CloudSendVo cloudSend = JsonConvert.Json2Object(message, CloudSendVo.class);
			String channelId = cloudSend.getChannelId();
			Channel channel = ChannelDefendUtils.getChannel(channelId);
			channel.writeAndFlush(message);
		} catch (IOException e) {
			LOGGER.info("转化异常", e);
			throw new ServiceException();
		}
		
	}
}
