package com.netty.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.netty.cloud.mapper.RtSpeedTestLogMapper;
import com.netty.cloud.pojo.RtSpeedTestLog;
import com.netty.util.PrimaryKeyUtils;
import com.netty.util.SealObjectUtils;
import com.netty.vo.ReceiveVo;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
/**
 * 接受哨兵测速结果处理类
 * @author CYQ
 *
 */
@Service
@Sharable
public class SpeedTestServerHandler extends SimpleChannelInboundHandler<ReceiveVo> {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpeedTestServerHandler.class);
	@Autowired
	private RtSpeedTestLogMapper rtSpeedTestLogMapper;
	@Autowired
	private PrimaryKeyUtils primaryKey;
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ReceiveVo base) throws Exception {
		System.out.println(333);
		if (!"testSpeedResult".equals(base.getMsgCode())) {
			ctx.fireChannelRead(base);
			return;
		}
		LOGGER.info("测速信息");
		RtSpeedTestLog rtSpeedTestLog = SealObjectUtils.getObject(base, RtSpeedTestLog.class);
		//查询之前是否已经报道过，已有则更新原有数据表，没有则插入
		QueryWrapper<RtSpeedTestLog> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("rt_mac_addr", base.getMac());
		RtSpeedTestLog logDB = rtSpeedTestLogMapper.selectOne(queryWrapper);		
		if (logDB != null) {
			rtSpeedTestLog.setStId(logDB.getStId());
			rtSpeedTestLogMapper.updateById(rtSpeedTestLog);
			return;
		}
		rtSpeedTestLog.setStId(primaryKey.getPrimaryKey());
		rtSpeedTestLogMapper.insert(rtSpeedTestLog);
	}

}
