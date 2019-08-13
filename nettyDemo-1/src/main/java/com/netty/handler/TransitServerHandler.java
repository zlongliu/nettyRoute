package com.netty.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netty.cloud.exception.ServiceException;
import com.netty.vo.ReceiveVo;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
/**
 * 中转serverhandler处理类
 * @author CYQ
 *
 */
@Service
@Sharable
public class TransitServerHandler extends SimpleChannelInboundHandler<ReceiveVo> {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseServerHandler.class);
	@Autowired
	private ReportServerHandler reportServerHandler;
	@Autowired
	private DownEqpServerHandler downEqpServerHandler;
	@Autowired
	private HeartBeatServerHandler heartBeatServiceHandler;
	@Autowired
	private PerformServerHandler performServerHandler;
	@Autowired
	private SpeedTestServerHandler speedTestServerHandler;
	/**
	 * 依据msgCode调用不同的handler处理
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ReceiveVo base) throws Exception {
		LOGGER.info("进入中转站");
		String msgCode = base.getMsgCode();
		if (msgCode == null) {
			LOGGER.info("msgCode为空");
			throw new ServiceException();
		}
		switch (msgCode) {
		case "selectConnectEquipmentResult" : downEqpServerHandler.channelRead0(ctx, base);break;
		case "heartBeat" : heartBeatServiceHandler.channelRead0(ctx, base);break;
		case "selectWANResult" : 
		case "routesPerformanceResult" : performServerHandler.channelRead0(ctx, base);break;
		case "reportMsg" : reportServerHandler.channelRead0(ctx, base);break;
		case "testSpeedResult": speedTestServerHandler.channelRead0(ctx, base);break;
		default : LOGGER.info("msgCode不在范围内");throw new ServiceException();
		}
		
	}

}
