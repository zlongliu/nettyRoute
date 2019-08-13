package com.netty.server;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.netty.util.SendOrderUtils;

import redis.clients.jedis.Jedis;
/**
 * 用户监听消息队列和向通道发送消息类
 * @author CYQ
 *
 */
@Component
public class NettyServerSocketReceive implements Runnable{
	private static final Logger LOGGER = LoggerFactory.getLogger(NettyServerSocketReceive.class);
	//主机名
	@Value("${redis.hostName}")
	private String hostName;
	@Autowired
	@Qualifier("jedisMq")
	private Jedis jedisMq;
	public NettyServerSocketReceive() {
	}
	public void active() {
		try {
			List<String> receives = jedisMq.brpop(0, hostName);
			LOGGER.info("接到数据啦", receives);
			String message = receives.get(1);
			SendOrderUtils.send(message);	
		} catch (Exception e) {
			LOGGER.info("jedisMq: redis关闭", e);
		}
	}
	@Override
	public void run() {
		while(true) {
			active();
		}
	}
	
}
