package com.netty.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;
/**
 * 主键生成类
 * @author CYQ
 *
 */
@Component
public class PrimaryKeyUtils {
	private Object object = new Object();
	/**
	 * 生成主键
	 * @return
	 */
	public Long getPrimaryKey() {
		String date = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
		StringBuffer sb = new StringBuffer(date);
		Integer ran = new Random().nextInt(1000000);
		String random = ran.toString();
		synchronized (object) {
			if (random.length()<6) {
				int poor = 6 - random.length();
				for (int i = 0; i < poor; i++) {
					sb.append("0");
				}
			}
			sb.append(random);
		}
		return Long.parseLong(sb.toString());
	}
}
