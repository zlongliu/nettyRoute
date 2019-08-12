package com.netty.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;
/**
 * 通道维护工具类
 * @author CYQ
 *
 */
public abstract class ChannelDefendUtils {
	/**
	 * 封装channelId和channel的ConcurrentHashMap
	 */
	private static Map<String, Channel> channelMap = new ConcurrentHashMap<>();
	/**
	 * 插入channelId和channel的键值对
	 * @param channelId
	 * @param channel
	 */
	public static void put(String channelId, Channel channel) {
		channelMap.put(channelId, channel);
	}
	/**
	 * 通过channelId获取channel对象
	 * @param channelId
	 * @return
	 */
	public static Channel getChannel(String channelId) {
		return channelMap.get(channelId);
	}
	public static Channel removeChannel(String channelId) {
		return channelMap.remove(channelId);
	}
	/**
	 * 获取channelMap对象
	 * @return
	 */
	public static Map<String, Channel> getChannelMap() {
		return channelMap;
	}
}
