package com.payment_service.service;


import com.payment_service.client.OrderFeignClient;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentService {

    private final OrderFeignClient orderFeignClient;

    public PaymentService(OrderFeignClient orderFeignClient) {
        this.orderFeignClient = orderFeignClient;
    }

    public Session createCheckoutSession(Long orderId, Long amount) {
        SessionCreateParams params = SessionCreateParams.builder().setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:8084/api/v1/payment/success?orderId=" + orderId)
                .setCancelUrl("http://localhost:8084/api/v1/payment/cancel")
                .addLineItem(SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency("usd")
                                        .setUnitAmount(amount)
                                        .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                .setName("Order "+ orderId)
                                                .build())
                                        .build())
                        .build())
                .build();

        try{
            return Session.create(params);
        }catch (Exception e){
            throw new RuntimeException("Failed to create checkout session", e);
        }
    }


    public boolean markPaymentAsSuccessful(Long orderId) {
       boolean status = orderFeignClient.updateOrderStatus(orderId);
        return status;
    }
}
