//package proxy;
//
//import lombok.SneakyThrows;
//import net.bytebuddy.ByteBuddy;
//import net.bytebuddy.implementation.InvocationHandlerAdapter;
//
///**
// * @author bigwillc
// */
//public class RpcByteBuddy extends RpcProxy implements RpcClient {
//
//    @Override
//    public <T> T create(Class<T> serviceClass, String url) {
//        if (!isExit(serviceClass.getName())) {
//            add(serviceClass.getName(), newProxy(serviceClass, url));
//        }
//        return (T) getProxy(serviceClass.getName());
//    }
//
//    @SneakyThrows
//    private <T> T newProxy(Class<T> serviceClass, String url) {
//        return (T) new ByteBuddy().subclass(Object.class)
//                .implement(serviceClass)
//                .intercept(InvocationHandlerAdapter.of(new RpcInvocationHandler(serviceClass, url)))
//                .make()
//                .load(RpcByteBuddy.class.getClassLoader())
//                .getLoaded()
//                .getDeclaredConstructor()
//                .newInstance();
//    }
//}
