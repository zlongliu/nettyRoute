package com.netty.cloud.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 下挂设备对应java类
 * @author CYQ
 *
 */
@TableName("rt_down_equipment")
public class RtDownEquipment implements Serializable{
	private static final long serialVersionUID = 1L;
	@TableId
	private Long eqpId;
	private String rtMacAddr;
	private String eqpHostname;
	private String eqpMacAddr;
	private String accDownloadTraffic;
	private String accUploadTraffic;
	private Date linkTime;
	private String linkMode;
	private Date updateTime;
	public Long getEqpId() {
		return eqpId;
	}
	public String getRtMacAddr() {
		return rtMacAddr;
	}
	public String getEqpHostname() {
		return eqpHostname;
	}
	public String getEqpMacAddr() {
		return eqpMacAddr;
	}
	public String getAccDownloadTraffic() {
		return accDownloadTraffic;
	}
	public String getAccUploadTraffic() {
		return accUploadTraffic;
	}
	public Date getLinkTime() {
		return linkTime;
	}
	public String getLinkMode() {
		return linkMode;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public RtDownEquipment setEqpId(Long eqpId) {
		this.eqpId = eqpId;
		return this;
	}
	public RtDownEquipment setRtMacAddr(String rtMacAddr) {
		this.rtMacAddr = rtMacAddr;
		return this;
	}
	public RtDownEquipment setEqpHostname(String eqpHostName) {
		this.eqpHostname = eqpHostName;
		return this;
	}
	public RtDownEquipment setEqpMacAddr(String eqpMacAddr) {
		this.eqpMacAddr = eqpMacAddr;
		return this;
	}
	public RtDownEquipment setAccDownloadTraffic(String accDownloadTraffic) {
		this.accDownloadTraffic = accDownloadTraffic;
		return this;
	}
	public RtDownEquipment setAccUploadTraffic(String accUploadTraffic) {
		this.accUploadTraffic = accUploadTraffic;
		return this;
	}
	public RtDownEquipment setLinkTime(Date linkTime) {
		this.linkTime = linkTime;
		return this;
	}
	public RtDownEquipment setLinkMode(String linkMode) {
		this.linkMode = linkMode;
		return this;
	}
	public RtDownEquipment setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eqpMacAddr == null) ? 0 : eqpMacAddr.hashCode());
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
		RtDownEquipment other = (RtDownEquipment) obj;
		if (eqpMacAddr == null) {
			if (other.eqpMacAddr != null)
				return false;
		} else if (!eqpMacAddr.equals(other.eqpMacAddr))
			return false;
		if (rtMacAddr == null) {
			if (other.rtMacAddr != null)
				return false;
		} else if (!rtMacAddr.equals(other.rtMacAddr))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RtDownEquipment [eqpId=" + eqpId + ", rtMacAddr=" + rtMacAddr + ", eqpHostname=" + eqpHostname
				+ ", eqpMacAddr=" + eqpMacAddr + ", accDownloadTraffic=" + accDownloadTraffic + ", accUploadTraffic="
				+ accUploadTraffic + ", linkTime=" + linkTime + ", linkMode=" + linkMode + ", updateTime=" + updateTime
				+ "]";
	}
}
