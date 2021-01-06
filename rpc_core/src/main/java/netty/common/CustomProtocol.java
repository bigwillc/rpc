package netty.common;

import lombok.Data;

/**
 * Netty communication struct.
 *
 * @author bigwillc
 */
@Data
public class CustomProtocol {

    /**
     * length of data.
     */
    private int len;

    /**
     * content of data.
     */
    private byte[] content;

}
