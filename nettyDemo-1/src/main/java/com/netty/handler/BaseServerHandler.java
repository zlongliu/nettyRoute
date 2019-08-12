package com.netty.handler;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.netty.util.ChannelDefendUtils;
import com.netty.util.JsonConvert;
import com.netty.vo.ReceiveVo;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;
/**
 * 获取客户端数据
 * @author CYQ
 *
 */
@Service
@Sharable
public class BaseServerHandler extends SimpleChannelInboundHandler<String> {
	/**
	 * 连接完成时调用
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		System.out.println(channel.id() + "已连接");
	}
	/**
	 * 连接断开时调用
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		ChannelDefendUtils.removeChannel(String.valueOf(channel.id()));
		System.out.println(channel.id() + "已离线");
	}
	/**
	 * 接收到哨兵数据时调用
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println(111);
		if (StringUtils.isEmpty(msg)) {
			return;
		}
		ReceiveVo base = JsonConvert.Json2Object(msg, ReceiveVo.class);
		if (base != null) {
			ctx.fireChannelRead(base);
		}
		
	}
	/**
	 * 出现异常时调用
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {	
		super.exceptionCaught(ctx, cause);
	}
	

}
