package com.netty.cloud.vo;

import java.io.Serializable;
/**
 * 统一返回值类
 * @author CYQ
 *
 */
public class JsonResultVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String rtnCode;
	private String rtnMsg;
	private String installFlag;
	private Object Result;
	public JsonResultVo() {
	}
	public JsonResultVo(String rtnCode, String rtnMsg, String installFlag, Object result) {
		this.rtnCode = rtnCode;
		this.rtnMsg = rtnMsg;
		this.installFlag = installFlag;
		Result = result;
	}
	public String getRtnCode() {
		return rtnCode;
	}
	public String getRtnMsg() {
		return rtnMsg;
	}
	public String getInstallFlag() {
		return installFlag;
	}
	public Object getResult() {
		return Result;
	}
	public void setRtnCode(String rtnCode) {
		this.rtnCode = rtnCode;
	}
	public JsonResultVo setRtnMsg(String rtnMsg) {
		this.rtnMsg = rtnMsg;
		return this;
	}
	public JsonResultVo setInstallFlag(String installFlag) {
		this.installFlag = installFlag;
		return this;
	}
	public JsonResultVo setResult(Object result) {
		Result = result;
		return this;
	}
	@Override
	public String toString() {
		return "JsonResultVo [rtnCode=" + rtnCode + ", rtnMsg=" + rtnMsg + ", installFlag=" + installFlag + ", Result="
				+ Result + "]";
	}
	public static JsonResultVo success() {
		return new JsonResultVo("0000", "成功", null, null);
	}
	public static JsonResultVo success(String installFlag, Object result) {
		return new JsonResultVo("0000", "成功", installFlag, null);
	}
	public static JsonResultVo success(Object result) {
		return new JsonResultVo("0000", "成功", null, result);
	}
	public static JsonResultVo fail() {
		return new JsonResultVo("-9999", "失败", null, null);
	}
}
