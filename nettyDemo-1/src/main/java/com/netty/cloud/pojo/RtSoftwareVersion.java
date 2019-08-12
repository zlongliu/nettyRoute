package com.netty.cloud.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class RtSoftwareVersion implements Serializable{
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.AUTO)
	private Long versionId;
	private String softwareName;
	private String softwareCode;
	private String softwareUrl;
	private BigDecimal softwareVersion;
	private Integer softwareType;
	private Date updateTime;
	public Long getVersionId() {
		return versionId;
	}
	public String getSoftwareName() {
		return softwareName;
	}
	public String getSoftwareCode() {
		return softwareCode;
	}
	public String getSoftwareUrl() {
		return softwareUrl;
	}
	public BigDecimal getSoftwareVersion() {
		return softwareVersion;
	}
	public Integer getSoftwareType() {
		return softwareType;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public RtSoftwareVersion setVersionId(Long versionId) {
		this.versionId = versionId;
		return this;
	}
	public RtSoftwareVersion setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
		return this;
	}
	public RtSoftwareVersion setSoftwareCode(String softwareCode) {
		this.softwareCode = softwareCode;
		return this;
	}
	public RtSoftwareVersion setSoftwareUrl(String softwareUrl) {
		this.softwareUrl = softwareUrl;
		return this;
	}
	public RtSoftwareVersion setSoftwareVersion(BigDecimal softwareVersion) {
		this.softwareVersion = softwareVersion;
		return this;
	}
	public RtSoftwareVersion setSoftwareType(Integer softwareType) {
		this.softwareType = softwareType;
		return this;
	}
	public RtSoftwareVersion setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((softwareName == null) ? 0 : softwareName.hashCode());
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
		RtSoftwareVersion other = (RtSoftwareVersion) obj;
		if (softwareName == null) {
			if (other.softwareName != null)
				return false;
		} else if (!softwareName.equals(other.softwareName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RtSoftwareVersion [versionId=" + versionId + ", softwareName=" + softwareName + ", softwareCode="
				+ softwareCode + ", softwareUrl=" + softwareUrl + ", softwareVersion=" + softwareVersion
				+ ", softwareType=" + softwareType + ", updateTime=" + updateTime + "]";
	}
}
