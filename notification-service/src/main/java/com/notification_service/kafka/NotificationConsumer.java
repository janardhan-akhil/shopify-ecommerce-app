package com.notification_service.kafka;

import com.notification_service.dto.OrderEvent;
import com.notification_service.service.EmailService;
import com.notification_service.service.SmsService;
import com.notification_service.service.WhatsAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final EmailService emailService;
    private final SmsService smsService;
    private final WhatsAppService whatsAppService;

    @KafkaListener(topics = "order-events", groupId = "notification-group-v2")
    public void consume(OrderEvent event) {
        System.out.println("Event Received : " + event.getEmail());
        if(event.getStatus().equalsIgnoreCase("SUCCESS")){
            emailService.sendEmail(event.getEmail(), "Transaction Completed", "Order Placed Successfully:"+event.getOrderId());
            smsService.sendSms(event.getMobile(), "Order Placed Successfully:"+event.getOrderId());
            whatsAppService.sendWhatsAppMessage(event.getToWhatsapp(), "Order Placed Successfully:"+event.getOrderId());

        }else{
            emailService.sendEmail(event.getEmail(), "Transaction Failed", "Order Placement Failed:"+event.getOrderId());
            smsService.sendSms(event.getMobile(), "Order Placement Failed:"+event.getOrderId());
            whatsAppService.sendWhatsAppMessage(event.getToWhatsapp(), "Order Placement Failed:"+event.getOrderId());
        }

        String subject = "Order Status Update";
        String body = "Order ID : " + event.getOrderId() + "\nStatus : " + event.getStatus();
    }
}

