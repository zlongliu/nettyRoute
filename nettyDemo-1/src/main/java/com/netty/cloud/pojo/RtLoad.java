package com.netty.cloud.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 路由器性能对应java对象
 * @author CYQ
 *
 */
@TableName("rt_load")
public class RtLoad implements Serializable{
	private static final long serialVersionUID = 1L;
	@TableId
	private Long loadId;
	private String rtMacAddr;
	private String wanRate;
	private String cpuPct;
	private String memoryPct;
	private String uploadTraffic;
	private String downloadTraffic;
	private Date sampleTime;
	public Long getLoadId() {
		return loadId;
	}
	public String getRtMacAddr() {
		return rtMacAddr;
	}
	public String getWanRate() {
		return wanRate;
	}
	public String getCpuPct() {
		return cpuPct;
	}
	public String getMemoryPct() {
		return memoryPct;
	}
	public String getUploadTraffic() {
		return uploadTraffic;
	}
	public String getDownloadTraffic() {
		return downloadTraffic;
	}
	public Date getSampleTime() {
		return sampleTime;
	}
	public RtLoad setLoadId(Long loadId) {
		this.loadId = loadId;
		return this;
	}
	public RtLoad setRtMacAddr(String rtMacAddr) {
		this.rtMacAddr = rtMacAddr;
		return this;
	}
	public RtLoad setWanRate(String wanRate) {
		this.wanRate = wanRate;
		return this;
	}
	public RtLoad setCpuPct(String cpuPct) {
		this.cpuPct = cpuPct;
		return this;
	}
	public RtLoad setMemoryPct(String memoryPct) {
		this.memoryPct = memoryPct;
		return this;
	}
	public RtLoad setUploadTraffic(String uploadTraffic) {
		this.uploadTraffic = uploadTraffic;
		return this;
	}
	public RtLoad setDownloadTraffic(String downloadTraffic) {
		this.downloadTraffic = downloadTraffic;
		return this;
	}
	public RtLoad setSampleTime(Date sampleTime) {
		this.sampleTime = sampleTime;
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
		RtLoad other = (RtLoad) obj;
		if (rtMacAddr == null) {
			if (other.rtMacAddr != null)
				return false;
		} else if (!rtMacAddr.equals(other.rtMacAddr))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "rtLoad [loadId=" + loadId + ", rtMacAddr=" + rtMacAddr + ", wanRate=" + wanRate + ", cpuPct=" + cpuPct
				+ ", memoryPct=" + memoryPct + ", uploadTraffic=" + uploadTraffic + ", downloadTraffic="
				+ downloadTraffic + ", sampleTime=" + sampleTime + "]";
	}
	
}
