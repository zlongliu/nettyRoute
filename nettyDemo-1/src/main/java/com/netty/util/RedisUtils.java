package com.netty.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.Jedis;
@Configuration
public class RedisUtils {
	@Value("${redis.host}")
	private String host;
	@Value("${redis.port}")
	private Integer port;
	@Bean
	public Jedis jedis() {
		Jedis jedis = new Jedis(host, port);
		return jedis;
	}
	@Bean("jedisMq")
	public Jedis getJedis() {
		Jedis jedis = new Jedis(host, port);
		return jedis;
	}
}
