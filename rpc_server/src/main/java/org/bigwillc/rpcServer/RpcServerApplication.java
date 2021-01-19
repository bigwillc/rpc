package org.bigwillc.rpcServer;

import lombok.extern.slf4j.Slf4j;
import netty.server.RpcNettyServer;
import org.bigwillc.rpcServer.service.impl.OrderServiceImpl;
import org.bigwillc.rpcServer.service.impl.UserServiceImpl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import service.OrderService;
import service.UserService;

@SpringBootApplication
@ComponentScan(value = "netty.server")
@Slf4j
public class RpcServerApplication implements ApplicationRunner {

    private final RpcNettyServer rpcNettyServer;

    public RpcServerApplication(RpcNettyServer rpcNettyServer) {
        this.rpcNettyServer = rpcNettyServer;
    }

    public static void main(String[] args) {
        SpringApplication.run(RpcServerApplication.class, args);
    }

    public void run(ApplicationArguments args) {
        try {
            rpcNettyServer.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            rpcNettyServer.destroy();
        }
    }

    @Bean("service.UserService")
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean("service.OrderService")
    public OrderService orderService() {
        return new OrderServiceImpl();
    }

}
