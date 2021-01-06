package api;

import lombok.Data;

/**
 * Rpc request struct.
 *
 * @author bigwillc
 */
@Data
public class RpcRequest {

    /**
     * service configuration name of request
     */
    private String serviceClass;

    /**
     * method name of request
     */
    private String method;

    /**
     * params of request
     */
    private Object[] argv;
}
