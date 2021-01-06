package netty.client;

import api.RpcResponse;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import netty.common.CustomProtocol;

import java.util.concurrent.CountDownLatch;

/**
 * @author bigwillc
 */
@Slf4j
public class RpcClientSyncHandler extends SimpleChannelInboundHandler<CustomProtocol> {

    private CountDownLatch latch;
    private RpcResponse response;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CustomProtocol customProtocol) throws Exception {
        log.info("Netty client receive message: ");
        log.info("Message length: " + customProtocol.getLen());
        log.info("Message content: " + new String(customProtocol.getContent(), CharsetUtil.UTF_8));

        // convert rpc response string to RpcResponse object.
        RpcResponse rpcResponse = JSON.parseObject(new String(customProtocol.getContent(), CharsetUtil.UTF_8), RpcResponse.class);
        log.info("Netty client serializer: " + rpcResponse.toString());

        response = rpcResponse;
        latch.countDown();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    RpcResponse getResponse() throws InterruptedException {
        latch.await();
        return response;
    }
}
