package com.netty.cloud.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * 操作日志对应java对象
 * @author CYQ
 *
 */
public class RtOperateLog implements Serializable{
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.AUTO)
	private Long optLogId;
	private String rtMacAddr;
	private String optNm;
	private String opter;
	private Integer optType;
	private Date optTime;
	public Long getOptLogId() {
		return optLogId;
	}
	public String getRtMacAddr() {
		return rtMacAddr;
	}
	public String getOptNm() {
		return optNm;
	}
	public String getOpter() {
		return opter;
	}
	public Integer getOptType() {
		return optType;
	}
	public Date getOptTime() {
		return optTime;
	}
	public RtOperateLog setOptLogId(Long optLogId) {
		this.optLogId = optLogId;
		return this;
	}
	public RtOperateLog setRtMacAddr(String rtMacAddr) {
		this.rtMacAddr = rtMacAddr;
		return this;
	}
	public RtOperateLog setOptNm(String optNm) {
		this.optNm = optNm;
		return this;
	}
	public RtOperateLog setOpter(String opter) {
		this.opter = opter;
		return this;
	}
	public RtOperateLog setOptType(Integer optType) {
		this.optType = optType;
		return this;
	}
	public RtOperateLog setOptTime(Date optTime) {
		this.optTime = optTime;
		return this;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rtMacAddr == null) ? 0 : rtMacAddr.hashCode());
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
		RtOperateLog other = (RtOperateLog) obj;
		if (rtMacAddr == null) {
			if (other.rtMacAddr != null)
				return false;
		} else if (!rtMacAddr.equals(other.rtMacAddr))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RtOperateLog [optLogId=" + optLogId + ", rtMacAddr=" + rtMacAddr + ", optNm=" + optNm + ", opter="
				+ opter + ", optType=" + optType + ", optTime=" + optTime + "]";
	}
	
}
