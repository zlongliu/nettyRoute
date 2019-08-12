package com.netty.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ReceiveVo implements Serializable{

	private static final long serialVersionUID = 1L;
	private String from;
	private String to;
	private Date timeStamp;
	private String msgCode;
	private String mac;
	private Map<String, Object> content;
	private List<Map<String, Object>> contents;	
	public List<Map<String, Object>> getContents() {
		return contents;
	}
	public ReceiveVo setContents(List<Map<String, Object>> contents) {
		this.contents = contents;
		return this;
	}
	public Map<String, Object> getContent() {
		return content;
	}
	public ReceiveVo setContent(Map<String, Object> content) {
		this.content = content;
		return this;
	}
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public String getMsgCode() {
		return msgCode;
	}
	public String getMac() {
		return mac;
	}
	public ReceiveVo setFrom(String from) {
		this.from = from;
		return this;
	}
	public ReceiveVo setTo(String to) {
		this.to = to;
		return this;
	}
	public ReceiveVo setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
		return this;
	}
	public ReceiveVo setMsgCode(String msgCode) {
		this.msgCode = msgCode;
		return this;
	}
	public ReceiveVo setMac(String mac) {
		this.mac = mac;
		return this;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mac == null) ? 0 : mac.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReceiveVo other = (ReceiveVo) obj;
		if (mac == null) {
			if (other.mac != null)
				return false;
		} else if (!mac.equals(other.mac))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ReceiveVo [from=" + from + ", to=" + to + ", timeStamp=" + timeStamp + ", msgCode=" + msgCode + ", mac="
				+ mac + ", content=" + content + ", contents=" + contents + "]";
	}


}
