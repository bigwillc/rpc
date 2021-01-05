package org.bigwillc.rpc_server.service.impl;


import exception.CustomException;
import model.Order;
import service.OrderService;

/**
 * OrderService implementation.
 *
 * @author bigwillc
 */
public class OrderServiceImpl implements OrderService {

    @Override
    public Order findById(Integer id) {
        return new Order(1, "RPC", 1);
    }

    @Override
    public Order findError() {
        throw new CustomException("Custom exception");
    }
}
