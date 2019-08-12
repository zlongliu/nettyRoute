package com.netty;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NettyDemo1ApplicationTests {
	@Autowired
	private Jedis jedis;

	@Test
	public void contextLoads() {
		System.out.println(jedis);
		jedis.set("123", "456");
		System.out.println(jedis);
	}

}
