//package netty_demo;
//
//import org.springframework.stereotype.Controller;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class Singleton {
//    private final Map singletonObjects = new HashMap();
//
//    protected Object getSingleton(String beanName) {
//        Object singletonObject = this.singletonObjects.get(beanName);
//        if (singletonObject == null) {
//            synchronized (this.singletonObjects) {
//                singletonObject = singletonFactory.getObject();
//                this.earlySingletonObjects.put(beanName, singletonObject);
//            }
//        }
//        return (singletonObject != NULL_OBJECT ? singletonObject : null);
//    }
//
//    public boolean handle(ServletRequest req, ServletResponse res) {
//        String uri = ((HttpServletRequest) req).getResponseURI();
//        Object[] parameters = new Object[args.length];
//        for (int i = 0; i < args.length; i++) {
//            parameters[i] = req.getParameter(args[i]);
//        }
//
//        Object ctl = controller.newInstance(uri);
//        Object response = method.invoke(ctl, parameters);
//        res.getWriter().println(response.toString());
//        return true;
//    }
//
//
//}
