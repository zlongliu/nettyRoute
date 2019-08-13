package com.netty.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.Jedis;
/**
 * 获取jedis对象配置类
 * @author CYQ
 *
 */
@Configuration
public class RedisUtils {
	@Value("${redis.host}")
	private String host;
	@Value("${redis.port}")
	private Integer port;
	/**
	 * 获取向redis存取的jedis对象
	 * @return
	 */
	@Bean
	public Jedis jedis() {
		Jedis jedis = new Jedis(host, port);
		return jedis;
	}
	/**
	 * 获取监听消息队列的值对象（阻塞）
	 * @return
	 */
	@Bean("jedisMq")
	public Jedis getJedis() {
		Jedis jedis = new Jedis(host, port);
		return jedis;
	}
}
