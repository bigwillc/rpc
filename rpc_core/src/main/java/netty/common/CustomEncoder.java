package netty.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * Custom netty encoder.
 *
 * @author bigwillc
 */
@Slf4j
public class CustomEncoder extends MessageToByteEncoder<CustomProtocol> {

    /**
     * @param channelHandlerContext channel handler context
     * @param customProtocol custom communication struct data
     * @param byteBuf output date
     * @throws Exception exception
     */
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, CustomProtocol customProtocol, ByteBuf byteBuf) throws Exception {
        log.info("Netty encoder run...");
        byteBuf.writeInt(customProtocol.getLen());
        byteBuf.writeBytes(customProtocol.getContent());
    }
}
