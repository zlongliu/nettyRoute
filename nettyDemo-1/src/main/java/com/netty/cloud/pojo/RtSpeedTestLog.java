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
	private Long stId;					//测速主键
	private String rtMacAddr;			//路由器mac地址
	private String opter;				//操作人
	private Integer optType;			//操作类型
	private Date optTime;				//操作时间
	private Date stStartTime;			//测速开始时间
	private Date stEndTime;				//测速结束时间
	private String uploadRate;			//上传速率
	private String downloadRate;		//下载速率
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
