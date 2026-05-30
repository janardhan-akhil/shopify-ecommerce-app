package com.payment_service.controller;

import com.payment_service.service.PaymentService;
import com.stripe.model.checkout.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/checkout")
    public ResponseEntity<String> createCheckoutSession(@RequestParam Long orderId, @RequestParam Long amount) {
        Session session = paymentService.createCheckoutSession(orderId, amount);
        return ResponseEntity.ok(session.getUrl());
    }

    @GetMapping("/success")
    public String paymentSuccess(@RequestParam("orderId") Long orderId) {
        boolean status = paymentService.markPaymentAsSuccessful(orderId);
        return "Payment Success for Order ID: " + orderId;
    }

    @GetMapping("/cancel")
    public ResponseEntity<String> paymentCancel() {
        return ResponseEntity.ok("Payment Cancelled");
    }
}
