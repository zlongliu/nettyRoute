package com.netty.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.netty.vo.ReceiveVo;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import redis.clients.jedis.Jedis;
/**
 * 接受哨兵心跳消息类
 * @author CYQ
 *
 */
@Service
@Sharable
public class HeartBeatServerHandler extends SimpleChannelInboundHandler<ReceiveVo> {
	private static final Logger LOGGER = LoggerFactory.getLogger(HeartBeatServerHandler.class);
	@Value("${redis.hostName}")
	private String hostName;
	@Autowired
	private Jedis jedis;
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ReceiveVo base) throws Exception {
		String mac = base.getMac();
		Channel channel = ctx.channel();
		ChannelId channelId = channel.id();
		//将mac，hostname,channelid存入redis
		StringBuffer value = new StringBuffer();
		value.append(hostName).append("@").append(channelId);
		LOGGER.info(String.valueOf(value));
		jedis.setex(mac, 60, String.valueOf(value));		
		channel.writeAndFlush("OK");
	}

}
