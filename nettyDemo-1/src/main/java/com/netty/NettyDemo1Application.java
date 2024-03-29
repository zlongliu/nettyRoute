package com.netty;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.netty.cloud.mapper")
public class NettyDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(NettyDemo1Application.class, args);
	}

}
