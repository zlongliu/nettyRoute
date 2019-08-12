package com.netty.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.netty.cloud.mapper.RtInfoMapper;
import com.netty.cloud.pojo.RtInfo;
import com.netty.util.ChannelDefendUtils;
import com.netty.util.PrimaryKeyUtils;
import com.netty.util.SealObjectUtils;
import com.netty.vo.ReceiveVo;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
/**
 * 接收报道信息处理类
 * @author CYQ
 *
 */
@Sharable
@Service
public class ReportServerHandler extends SimpleChannelInboundHandler<ReceiveVo> {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportServerHandler.class);
	@Autowired
	private RtInfoMapper rtInfoMapper;
	@Autowired
	private PrimaryKeyUtils primaryKey;
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ReceiveVo base) throws Exception {
		if (!"reportMsg".equals(base.getMsgCode().trim())) {
			//传递到下一个处理器
			ctx.fireChannelRead(base);
			return;
		}
		LOGGER.info("报道消息");
		Channel channel = ctx.channel();
		ChannelId channelId = channel.id();
		ChannelDefendUtils.put(String.valueOf(channelId), channel);
		RtInfo info = SealObjectUtils.getObject(base, RtInfo.class);
		LOGGER.info(info.toString());
		//查询之前是否已经报道过，已有则更新原有数据表，没有则插入
		QueryWrapper<RtInfo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("rt_mac_addr", base.getMac()).or()
					.eq("broadband_acc", String.valueOf(base.getContent().get("broadband")));		
		RtInfo infoDB = rtInfoMapper.selectOne(queryWrapper);
		if (infoDB != null) {
			info.setRtId(infoDB.getRtId());
			rtInfoMapper.updateById(info);
			return;
		}
		info.setRtId(primaryKey.getPrimaryKey());
		rtInfoMapper.insert(info);
	}

}
