package com.netty.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.netty.cloud.mapper.RtLoadMapper;
import com.netty.cloud.pojo.RtLoad;
import com.netty.util.PrimaryKeyUtils;
import com.netty.util.SealObjectUtils;
import com.netty.vo.ReceiveVo;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
/**
 * 接收wan口速率/路由器性能消息处理类
 * @author CYQ
 *
 */
@Service
@Sharable
public class PerformServerHandler extends SimpleChannelInboundHandler<ReceiveVo> {
	private static final Logger LOGGER = LoggerFactory.getLogger(PerformServerHandler.class);
	@Autowired
	private RtLoadMapper rtLoadMapper;
	@Autowired
	private PrimaryKeyUtils primaryKey;
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ReceiveVo base) throws Exception {
		System.out.println(444);
		if (!("selectWANResult".equals(base.getMsgCode()) || "routesPerformanceResult".equals(base.getMsgCode()))) {
			ctx.fireChannelRead(base);
			return;
		}
		LOGGER.info("路由器性能");
		RtLoad rtLoad = SealObjectUtils.getObject(base, RtLoad.class);
		// 查询之前是否已经报道过，已有则更新原有数据表，没有则插入
		QueryWrapper<RtLoad> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("rt_mac_addr", base.getMac());
		RtLoad rtLoadDB = rtLoadMapper.selectOne(queryWrapper);		
		if (rtLoadDB != null) {
			rtLoad.setLoadId(rtLoadDB.getLoadId());
			rtLoadMapper.updateById(rtLoad);
			return;
		}
		rtLoad.setLoadId(primaryKey.getPrimaryKey());
		rtLoadMapper.insert(rtLoad);
	}

}
