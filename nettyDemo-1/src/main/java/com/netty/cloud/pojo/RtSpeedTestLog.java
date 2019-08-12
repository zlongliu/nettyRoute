package com.netty.cloud.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 测速表对用java对象
 * @author CYQ
 *
 */
@TableName("rt_speedtest_log")
public class RtSpeedTestLog implements Serializable{
	private static final long serialVersionUID = 1L;
	@TableId
	private Long stId;
	private String rtMacAddr;
	private String opter;
	private Integer optType;
	private Date optTime;
	private Date stStartTime;
	private Date stEndTime;
	private String uploadRate;
	private String downloadRate;
	public Long getStId() {
		return stId;
	}
	public String getRtMacAddr() {
		return rtMacAddr;
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
	public Date getStStartTime() {
		return stStartTime;
	}
	public Date getStEndTime() {
		return stEndTime;
	}
	public String getUploadRate() {
		return uploadRate;
	}
	public String getDownloadRate() {
		return downloadRate;
	}
	public RtSpeedTestLog setStId(Long stId) {
		this.stId = stId;
		return this;
	}
	public RtSpeedTestLog setRtMacAddr(String rtMacAddr) {
		this.rtMacAddr = rtMacAddr;
		return this;
	}
	public RtSpeedTestLog setOpter(String opter) {
		this.opter = opter;
		return this;
	}
	public RtSpeedTestLog setOptType(Integer optType) {
		this.optType = optType;
		return this;
	}
	public RtSpeedTestLog setOptTime(Date optTime) {
		this.optTime = optTime;
		return this;
	}
	public RtSpeedTestLog setStStartTime(Date stStartTime) {
		this.stStartTime = stStartTime;
		return this;
	}
	public RtSpeedTestLog setStEndTime(Date stEndTime) {
		this.stEndTime = stEndTime;
		return this;
	}
	public RtSpeedTestLog setUploadRate(String uploadRate) {
		this.uploadRate = uploadRate;
		return this;
	}
	public RtSpeedTestLog setDownloadRate(String downloadRate) {
		this.downloadRate = downloadRate;
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
		RtSpeedTestLog other = (RtSpeedTestLog) obj;
		if (rtMacAddr == null) {
			if (other.rtMacAddr != null)
				return false;
		} else if (!rtMacAddr.equals(other.rtMacAddr))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "rtSpeedTestLog [stId=" + stId + ", rtMacAddr=" + rtMacAddr + ", opter=" + opter + ", optType=" + optType
				+ ", optTime=" + optTime + ", stStartTime=" + stStartTime + ", stEndTime=" + stEndTime + ", uploadRate="
				+ uploadRate + ", downloadRate=" + downloadRate + "]";
	}
}
