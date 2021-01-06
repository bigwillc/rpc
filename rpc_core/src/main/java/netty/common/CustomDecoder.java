package netty.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Custom decoder.
 *
 * @author bigwillc
 */
@Slf4j
public class CustomDecoder extends ByteToMessageDecoder {

    private int length = 0;

    /**
     * @param channelHandlerContext channel handler context
     * @param byteBuf input stream
     * @param list output list
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        log.info("Netty decode run...");
        if (byteBuf.readableBytes() >= 4) {
            if (length == 0) {
                length = byteBuf.readInt();
            }
            if (byteBuf.readableBytes() < length) {
                log.info("Readable data is less, wait");
                return;
            }
            byte[] content = new byte[length];
            if (byteBuf.readableBytes() >= length) {
                byteBuf.readBytes(content);
                CustomProtocol customProtocol = new CustomProtocol();
                customProtocol.setLen(length);
                customProtocol.setContent(content);
                list.add(customProtocol);
            }
            length = 0;
        }

    }
}
