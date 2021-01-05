package org.bigwillc.rpc_server.config;

import org.bigwillc.rpc_server.service.impl.OrderServiceImpl;
import org.bigwillc.rpc_server.service.impl.UserServiceImpl;
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

    @Bean("com.example.demo.service.UserService")
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean("com.example.demo.service.OrderService")
    public OrderService orderService() {
        return new OrderServiceImpl();
    }
}
