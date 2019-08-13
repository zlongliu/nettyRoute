package com.netty.cloud.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 路由器对应java对象
 * @author CYQ
 *
 */
@TableName("rt_info")
public class RtInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	@TableId
	private Long rtId;						//路由器主键
	private String rtMacAddr;				//路由器mac地址
	private String broadbandAcc;			//宽带账号
	private String rtBrand;					//路由器品牌
	private String rtModel;					//路由器型号
	private String rtSn;					//路由器系列号
	private Double coreMhz;					//核心频率
	private Integer memorySize;				//内存
	@TableField("ssid_24g")
	private String ssid24G;					//2.4G无线账号
	@TableField("ssid_5g")
	private String ssid5G;					//5G无线账号
	private BigDecimal romVersion;			//固件版本
	private BigDecimal sentinelVersion;		//哨兵版本
	private Date deliveryTime;				//出厂时间
	private Date FirstReportTime;			//第一次报道时间
	private Date updateTime;				//修改时间
	public Long getRtId() {
		return rtId;
	}
	public String getRtMacAddr() {
		return rtMacAddr;
	}
	public String getBroadbandAcc() {
		return broadbandAcc;
	}
	public String getRtBrand() {
		return rtBrand;
	}
	public String getRtModel() {
		return rtModel;
	}
	public String getRtSn() {
		return rtSn;
	}
	public Double getCoreMhz() {
		return coreMhz;
	}
	public Integer getMemorySize() {
		return memorySize;
	}
	public String getSsid24G() {
		return ssid24G;
	}
	public String getSsid5G() {
		return ssid5G;
	}
	public BigDecimal getRomVersion() {
		return romVersion;
	}
	public BigDecimal getSentinelVersion() {
		return sentinelVersion;
	}
	public Date getDeliveryTime() {
		return deliveryTime;
	}
	public Date getFirstReportTime() {
		return FirstReportTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public RtInfo setRtId(Long rtId) {
		this.rtId = rtId;
		return this;
	}
	public RtInfo setRtMacAddr(String rtMacAddr) {
		this.rtMacAddr = rtMacAddr;
		return this;
	}
	public RtInfo setBroadbandAcc(String broadbandAcc) {
		this.broadbandAcc = broadbandAcc;
		return this;
	}
	public RtInfo setRtBrand(String rtBrand) {
		this.rtBrand = rtBrand;
		return this;
	}
	public RtInfo setRtModel(String rtModel) {
		this.rtModel = rtModel;
		return this;
	}
	public RtInfo setRtSn(String rtSn) {
		this.rtSn = rtSn;
		return this;
	}
	public RtInfo setCoreMhz(Double codeMhz) {
		this.coreMhz = codeMhz;
		return this;
	}
	public RtInfo setMemorySize(Integer memorySize) {
		this.memorySize = memorySize;
		return this;
	}
	public RtInfo setSsid24G(String ssid24g) {
		ssid24G = ssid24g;
		return this;
	}
	public RtInfo setSsid5G(String ssid5g) {
		ssid5G = ssid5g;
		return this;
	}
	public RtInfo setRomVersion(BigDecimal romVersion) {
		this.romVersion = romVersion;
		return this;
	}
	public RtInfo setSentinelVersion(BigDecimal sentinelVersion) {
		this.sentinelVersion = sentinelVersion;
		return this;
	}
	public RtInfo setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
		return this;
	}
	public RtInfo setFirstReportTime(Date firstReportTime) {
		FirstReportTime = firstReportTime;
		return this;
	}
	public RtInfo setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
		RtInfo other = (RtInfo) obj;
		if (rtMacAddr == null) {
			if (other.rtMacAddr != null)
				return false;
		} else if (!rtMacAddr.equals(other.rtMacAddr))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RtInfo [rtId=" + rtId + ", rtMacAddr=" + rtMacAddr + ", broadbandAcc=" + broadbandAcc + ", rtBrand="
				+ rtBrand + ", rtModel=" + rtModel + ", rtSn=" + rtSn + ", coreMhz=" + coreMhz + ", memorySize="
				+ memorySize + ", ssid24G=" + ssid24G + ", ssid5G=" + ssid5G + ", romVersion=" + romVersion
				+ ", sentinelVersion=" + sentinelVersion + ", deliveryTime=" + deliveryTime + ", FirstReportTime="
				+ FirstReportTime + ", updateTime=" + updateTime + "]";
	}
	
}
