package com.netty.server;

import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netty.handler.BaseServerHandler;
import com.netty.handler.DownEqpServerHandler;
import com.netty.handler.HeartBeatServerHandler;
import com.netty.handler.PerformServerHandler;
import com.netty.handler.ReportServerHandler;
import com.netty.handler.SpeedTestServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
/**
 * 云端服务端启动项
 * @author CYQ
 *
 */
@Component
public class NettyServerSocket {
	private final int port = 8090;
	private EventLoopGroup bossGroup = new NioEventLoopGroup(1);
	private EventLoopGroup workerGroup = new NioEventLoopGroup();
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
	@Autowired
	private NettyServerSocketReceive socketReceive;
	@Autowired
	private BaseServerHandler baseServerHandler;
	public NettyServerSocket() {
		new Thread() {
			public void run() {
				init();
			}
		}.start();
	}
	public void start() throws Exception {
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.localAddress(new InetSocketAddress(port))
				.handler(new LoggingHandler(LogLevel.INFO))
				.option(ChannelOption.SO_BACKLOG, 1024)//连接满时，已经完成三次握手的连接最大等待数
				.option(ChannelOption.SO_KEEPALIVE, true)//长连接
				.option(ChannelOption.SO_TIMEOUT, 3)//客户端连接超时
				.option(ChannelOption.TCP_NODELAY, true)//不用等待缓存区满，直接发送数据
				.childHandler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline()
							.addLast("decoder", new StringDecoder())
							.addLast("encoder",new StringEncoder())
							.addLast(new ReadTimeoutHandler(60))							
							.addLast(baseServerHandler)
							.addLast(heartBeatServiceHandler)
							.addLast(downEqpServerHandler)
							.addLast(reportServerHandler)
							.addLast(speedTestServerHandler)
							.addLast(performServerHandler);
						
					}
				});
			ChannelFuture future = b.bind().sync();
			if (future.isSuccess()) {
				System.out.println("server: 已启动");
			}else {
				System.out.println("server: 启动失败");
			}
			
			new Thread(socketReceive).start();
			future.channel().closeFuture().sync();
		} finally {
			destory();
		}
	}
	private void init() {
		try {
			start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void destory() {
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
		System.out.println("server: 已关闭");
	}

}
