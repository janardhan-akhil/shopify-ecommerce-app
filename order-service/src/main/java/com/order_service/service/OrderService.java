package com.order_service.service;

import com.order_service.entity.Order;

public interface OrderService {
    public Order createOrder(String uuid);

   public  boolean updateOrderStatus(Long orderId);
}
