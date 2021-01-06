package netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import netty.common.CustomDecoder;
import netty.common.CustomEncoder;

/**
 * @author bigwillc
 */
public class RpcClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("Message Encoder", new CustomEncoder());
        pipeline.addLast("Message Decoder", new CustomDecoder());
        pipeline.addLast("clientHandler", new RpcClientSyncHandler());
    }
}
