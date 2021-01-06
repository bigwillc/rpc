package netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import netty.common.CustomDecoder;
import netty.common.CustomEncoder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Netty server starter.
 *
 * @author bigwillc
 */
@Slf4j
@Component
public class RpcNettyServer {

    private final ApplicationContext context;

    private EventLoopGroup boss;
    private EventLoopGroup worker;

    public RpcNettyServer(ApplicationContext context) {
        this.context = context;
    }

    public void destroy() {
        worker.shutdownGracefully();
        boss.shutdownGracefully();
    }

    public void run() throws Exception {
        boss = new NioEventLoopGroup(1);
        worker = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception{
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast("Message encoder", new CustomEncoder());
                        pipeline.addLast("Message decoder", new CustomDecoder());
                        pipeline.addLast("Message handler", new RpcServerHandler(context));
                    }
                });
        int port = 8080;
        // make a connection.
        Channel channel = serverBootstrap.bind(port).sync().channel();
        log.info("Netty server listen in port: " + port);
        // if can not connect port, close this connection.
        channel.closeFuture().sync();
    }
}