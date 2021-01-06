package netty.server;

import api.RpcRequest;
import api.RpcResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import exception.CustomException;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import netty.common.CustomProtocol;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author bigwillc
 */
@Slf4j
public class RpcServerHandler extends SimpleChannelInboundHandler<CustomProtocol> {

    private ApplicationContext applicationContext;

    RpcServerHandler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CustomProtocol msg) throws Exception {
        log.info("Netty server receive message");
        log.info("Message length: " + msg.getLen());
        log.info("Message length: " + new String(msg.getContent(), CharsetUtil.UTF_8));

        // acquire content of rpcRequest and deserial to rpcRequest object.
        RpcRequest rpcRequest = JSON.parseObject(new String(msg.getContent(), CharsetUtil.UTF_8), RpcRequest.class);
        log.info("Netty server serializer: " + rpcRequest.toString());

        // acquire related bean, and invoke reflect method
        RpcResponse response = invoke(rpcRequest);

        CustomProtocol message = new CustomProtocol();
        String requestJson = JSON.toJSONString(response);
        message.setLen(requestJson.getBytes(CharsetUtil.UTF_8).length);
        message.setContent(requestJson.getBytes(CharsetUtil.UTF_8));

        channelHandlerContext.writeAndFlush(message).sync();
        log.info("return response to client end");

    }

    /**
     * acquire implementation of related bean, invoke method by reflecting
     *
     * @param request rpc request
     * @return result of response
     */
    private RpcResponse invoke(RpcRequest request) {
        RpcResponse response = new RpcResponse();
        String serviceClass = request.getServiceClass();

        // using generics and reflect
        Object service = applicationContext.getBean(serviceClass);
        try {
            Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
            Object result = method.invoke(service, request.getArgv());
            log.info("Server method invoke result: " + result.toString());

            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            response.setStatus(true);

            log.info("Server response serialize to string return");
            return response;
        } catch (IllegalAccessException | InvocationTargetException | CustomException e) {
            e.printStackTrace();
            response.setException(e);
            response.setStatus(false);
            return response;
        }
    }

    /**
     * @param klass meta date of class
     * @param methodName resolve method name
     * @return method
     */
    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }
}
