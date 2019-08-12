package com.netty.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.netty.cloud.pojo.RtDownEquipment;
import com.netty.cloud.pojo.RtInfo;
import com.netty.cloud.pojo.RtLoad;
import com.netty.cloud.pojo.RtSpeedTestLog;
import com.netty.vo.ReceiveVo;
/**
 * 数据库转化对象操作类
 * @author CYQ
 *
 */
public abstract class SealObjectUtils {
	/**
	 * 获取封装指定对象
	 * @param <T>
	 * @param base
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T>T getObject(ReceiveVo base, Class<T> cls) throws Exception {
		T obj = cls.newInstance();
		if (obj instanceof RtInfo) {
			obj = (T) getRtInfo(base);
			return obj;
		}
		if (obj instanceof RtLoad) {
			obj = (T) getRtLoad(base);
			return obj;
		}
		if (obj instanceof RtSpeedTestLog) {
			obj = (T) getRtSpeed(base);
			return obj;
		}
		if (obj instanceof List) {
			obj = (T) getRtDownEqp(base);
			return obj;
		}
		return null;
	}
	/**
	 * 获得下挂设备集合
	 * @param base
	 * @return
	 * @throws Exception
	 */
	private static List<RtDownEquipment> getRtDownEqp(ReceiveVo base) throws Exception{
		List<Map<String, Object>> contents = base.getContents();
		List<RtDownEquipment> lists = new ArrayList<>();
		Date updateTime = new Date();
		for (Map<String, Object> content : contents) {
			RtDownEquipment eqp = new RtDownEquipment();
			eqp.setAccDownloadTraffic(String.valueOf(content.get("accDownloadTraffic")))
				.setAccUploadTraffic(String.valueOf(content.get("accUploadTraffic")))
				.setEqpHostname(String.valueOf(content.get("eqpHostname")))
				.setEqpMacAddr(String.valueOf(content.get("eqpMacAddr")))
				.setLinkMode(String.valueOf(content.get("linkMode")))
				.setRtMacAddr(base.getMac())
				.setLinkTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(content.get("linkTime"))))
				.setUpdateTime(updateTime);
			lists.add(eqp);
		}
		return lists;
	}
	/**
	 * 封装测速信息
	 * @param base
	 * @return
	 * @throws Exception
	 */
	private static RtSpeedTestLog getRtSpeed(ReceiveVo base) throws Exception{
		RtSpeedTestLog rtSpeedTestLog = new RtSpeedTestLog();
		Date optTime = new Date();
		rtSpeedTestLog.setDownloadRate(String.valueOf(base.getContent().get("downloadRate")))
						.setOpter("zhangsan")
						.setOptTime(optTime)
						.setOptType(1)
						.setRtMacAddr(base.getMac())
						.setStEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(base.getContent().get("stEndTime"))))
						.setStStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(base.getContent().get("stStartTime"))))
						.setUploadRate(String.valueOf(base.getContent().get("uploadRate")));
		return rtSpeedTestLog;
	}
	/**
	 * 封装路由器性能对象
	 * @param base
	 * @return
	 * @throws Exception
	 */
	private static RtLoad getRtLoad(ReceiveVo base) throws Exception{
		RtLoad rtLoad = new RtLoad();
		rtLoad.setCpuPct(String.valueOf(base.getContent().get("cpuPct")))
				.setDownloadTraffic(String.valueOf(base.getContent().get("downloadTraffic")))
				.setMemoryPct(String.valueOf(base.getContent().get("memoryPct")))
				.setRtMacAddr(base.getMac())
				.setUploadTraffic(String.valueOf(base.getContent().get("uploadTraffic")))
				.setWanRate(String.valueOf(base.getContent().get("wanRate")))
				.setSampleTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(base.getContent().get("sampleTime"))));
		return rtLoad;
	}
	/**
	 * 封装路由器详情对象
	 * @param base
	 * @return
	 * @throws Exception
	 */
	private static RtInfo getRtInfo(ReceiveVo base) throws Exception{
		RtInfo info = new RtInfo();
		info.setBroadbandAcc(String.valueOf(base.getContent().get("broadbandAcc")))
			.setCoreMhz(Double.parseDouble(String.valueOf(base.getContent().get("coreMhz")).replaceFirst("GHz", "")))
			.setDeliveryTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(base.getContent().get("deliveryTime"))))
			.setFirstReportTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(base.getContent().get("reportTime"))))
			.setMemorySize(Integer.parseInt(String.valueOf(base.getContent().get("memorySize")).replaceFirst("MB", "")))
			.setRomVersion(BigDecimal.valueOf(Double.parseDouble(String.valueOf(base.getContent().get("romVersion")).substring(1))))
			.setRtBrand(String.valueOf(base.getContent().get("rtBrand")))
			.setRtMacAddr(base.getMac())
			.setRtModel(String.valueOf(base.getContent().get("rtModel")))
			.setRtSn(String.valueOf(base.getContent().get("rtSn")))
			.setSentinelVersion(BigDecimal.valueOf(Double.parseDouble(String.valueOf(base.getContent().get("sentinelVersion")).substring(1))))
			.setSsid24G(String.valueOf(base.getContent().get("ssid24g")))
			.setSsid5G(String.valueOf(base.getContent().get("ssid5g")))
			.setUpdateTime(new Date());
		return info;
	}

}