package com.netty;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NettyDemo1ApplicationTests {
	@Autowired
	private StringEncryptor en;
	@Test
	public void contextLoads() {
		String result = en.encrypt("cyq121210");
		System.out.println(result);
	}

}
