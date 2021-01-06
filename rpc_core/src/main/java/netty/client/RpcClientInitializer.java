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
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("Message Encoder", new CustomEncoder());
        pipeline.addLast("Message Decoder", new CustomDecoder());
        pipeline.addLast("ClientHandler", new RpcClientSyncHandler());
    }
}
