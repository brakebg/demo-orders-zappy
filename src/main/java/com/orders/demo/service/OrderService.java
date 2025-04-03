package com.orders.demo.service;

import com.orders.demo.dto.OrderRequestDTO;

public interface OrderService {

    public boolean createOrder(OrderRequestDTO orderRequestDTO);
}
