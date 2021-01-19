package org.bigwillc.rpcServer.config;

import org.bigwillc.rpcServer.service.impl.OrderServiceImpl;
import org.bigwillc.rpcServer.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.OrderService;
import service.UserService;

/**
 * Configure implication class
 *
 * @author bigwillc
 */
@Configuration
public class BeanConfig {

    @Bean("service.UserService")
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean("service.OrderService")
    public OrderService orderService() {
        return new OrderServiceImpl();
    }
}
