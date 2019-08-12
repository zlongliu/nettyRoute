package com.netty.handler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.netty.cloud.mapper.RtDownEquipmentMapper;
import com.netty.cloud.pojo.RtDownEquipment;
import com.netty.util.PrimaryKeyUtils;
import com.netty.util.SealObjectUtils;
import com.netty.vo.ReceiveVo;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
/**
 * 接受哨兵下挂设备消息处理类
 * @author CYQ
 *
 */
@Service
@Sharable
public class DownEqpServerHandler extends SimpleChannelInboundHandler<ReceiveVo> {
	private static final Logger LOGGER = LoggerFactory.getLogger(DownEqpServerHandler.class);
	@Autowired
	private RtDownEquipmentMapper rtDownEqpMapper;
	@Autowired
	private PrimaryKeyUtils primaryKey;
	@SuppressWarnings("unchecked")
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ReceiveVo base) throws Exception {
		System.out.println(555);
		if (!"selectConnectEquipmentResult".equals(base.getMsgCode())) {
			ctx.fireChannelRead(base);
			return;
		}
		LOGGER.info("查询下挂设备");
		List<RtDownEquipment> lists = new ArrayList<>();
		lists = SealObjectUtils.getObject(base, lists.getClass());;
		QueryWrapper<RtDownEquipment> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("rt_mac_addr", base.getMac());
		List<RtDownEquipment> eqps = rtDownEqpMapper.selectList(queryWrapper);
		if (eqps.size() != 0) {
			List<Long> ids = new ArrayList<>();
			for (RtDownEquipment eqp : eqps) {
				ids.add(eqp.getEqpId());
			}
			rtDownEqpMapper.deleteBatchIds(ids);
		}
		for (RtDownEquipment rtDownEquipment : lists) {
			rtDownEquipment.setEqpId(primaryKey.getPrimaryKey());
			rtDownEqpMapper.insert(rtDownEquipment);
		}
	}

}
