package com.netty.vo;

import java.io.Serializable;

import io.netty.channel.Channel;

public class RedisVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String hostName;
	private Channel channel;
	public String getHostName() {
		return hostName;
	}
	public Channel getChannel() {
		return channel;
	}
	public RedisVo setHostName(String hostName) {
		this.hostName = hostName;
		return this;
	}
	public RedisVo setChannel(Channel channel) {
		this.channel = channel;
		return this;
	}
	@Override
	public String toString() {
		return "RedisVo [hostName=" + hostName + ", channel=" + channel + "]";
	}
}
