package com.order_service.service.impl;

import com.order_service.client.CartFeignClient;
import com.order_service.dto.CartItemResponse;
import com.order_service.dto.CartResponse;
import com.order_service.entity.Order;
import com.order_service.entity.OrderItem;
import com.order_service.repository.OrderRepository;
import com.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartFeignClient cartFeignClient;

    public OrderServiceImpl(OrderRepository orderRepository, CartFeignClient cartFeignClient) {
        this.orderRepository = orderRepository;
        this.cartFeignClient = cartFeignClient;
    }

    public Order createOrder(String uuid) {

        // 1. Get cart from cart service
        CartResponse cart = cartFeignClient.getCart(uuid);

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();

        order.setUuid(uuid);
        order.setUserId(cart.getUserId());
        order.setOrderStatus("PLACED");

        BigDecimal total = BigDecimal.ZERO;

        List<OrderItem> orderItems = new ArrayList<>();

        // 2. Convert cart item → order item
        for (CartItemResponse item : cart.getItems()) {
            OrderItem orderItem = new OrderItem();

            orderItem.setProductId(item.getProductId());
            orderItem.setBrandId(item.getBrandId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getPrice());

            orderItem.setOrder(order);
            orderItems.add(orderItem);

            total = total.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        order.setItems(orderItems);
        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);

        // 3. Clear cart after order
        cartFeignClient.clearCart(uuid);
        return savedOrder;
    }


    public boolean updateOrderStatus(Long orderId){
        Order order = orderRepository.findById(orderId).get();
        order.setOrderStatus("COMPLETED");
        Order savedOrder = orderRepository.save(order);
        return order.getOrderStatus().equals("COMPLETED");
    }
}
