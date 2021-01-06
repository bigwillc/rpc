package api;

import lombok.Data;

/**
 * Rpc response struct.
 *
 * @author bigwillc
 */
@Data
public class RpcResponse {

    /**
     * result of response
     */
    private Object result;

    /**
     * status of response
     */
    private Boolean status;

    /**
     * exception of response
     */
    private Exception exception;
}
