package proxy;

/**
 * @author bigwillc
 */
public interface RpcClient {

    /**
     * create proxy
     * @param serviceClass service class
     * @param url server url
     * @param <T> T
     * @return proxy class
     */
    <T> T create(final Class<T> serviceClass, final String url);
}
