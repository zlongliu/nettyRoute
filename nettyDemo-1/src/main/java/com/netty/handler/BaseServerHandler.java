package com.netty.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.netty.util.ChannelDefendUtils;
import com.netty.util.JsonConvert;
import com.netty.vo.ReceiveVo;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
/**
 * 获取客户端数据
 * @author CYQ
 *
 */
@Service
@Sharable
public class BaseServerHandler extends SimpleChannelInboundHandler<String> {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseServerHandler.class);
	/**
	 * 连接完成时调用
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		LOGGER.info(channel.id() + "已连接");
	}
	/**
	 * 连接断开时调用
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		ChannelDefendUtils.removeChannel(String.valueOf(channel.id()));
		LOGGER.info(channel.id() + "已离线");
	}
	/**
	 * 接收到哨兵数据时调用
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		if (StringUtils.isEmpty(msg)) {
			return;
		}
		ReceiveVo base = JsonConvert.Json2Object(msg, ReceiveVo.class);
		ctx.fireChannelRead(base);		
	}
	/**
	 * 出现异常时调用
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {	
		super.exceptionCaught(ctx, cause);
	}
	

}
