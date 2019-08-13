package com.netty.cloud.vo;

import java.io.Serializable;
/**
 * 云端统一发送消息值类
 * @author CYQ
 *
 */
public class CloudSendVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String from = "cloud";
	private String to = "client";
	private Long timeStamp;
	private String msgCode;
	private String channelId;
	private Object content;
	public CloudSendVo() {
	}
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	public String getMsgCode() {
		return msgCode;
	}
	public String getChannelId() {
		return channelId;
	}
	public Object getContent() {
		return content;
	}
	public CloudSendVo setFrom(String from) {
		this.from = from;
		return this;
	}
	public CloudSendVo setTo(String to) {
		this.to = to;
		return this;
	}
	public CloudSendVo setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
		return this;
	}
	public CloudSendVo setMsgCode(String msgCode) {
		this.msgCode = msgCode;
		return this;
	}
	public CloudSendVo setChannelId(String channelId) {
		this.channelId = channelId;
		return this;
	}
	public CloudSendVo setContent(Object content) {
		this.content = content;
		return this;
	}
	@Override
	public String toString() {
		return "CloudSendVo [from=" + from + ", to=" + to + ", timeStamp=" + timeStamp + ", msgCode=" + msgCode
				+ ", channelId=" + channelId + ", content=" + content + "]";
	}
}
