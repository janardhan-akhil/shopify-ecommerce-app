package com.notification_service.service;

import com.notification_service.config.TwilioConfig;
import com.notification_service.dto.WhatsAppRequest;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WhatsAppService {

  private final TwilioConfig  twilioConfig;

    public String sendWhatsAppMessage(WhatsAppRequest request) {
        try {
            Message message = Message.creator(new PhoneNumber("whatsapp:" + request.getToPhoneNumber()),
                            new PhoneNumber(twilioConfig.getFromWhatsappNumber()),
                            request.getMessage()).create();

            return "WhatsApp Sent Successfully : " + message.getSid();

        } catch (Exception e) {
            throw new RuntimeException("Failed To Send WhatsApp Message : " + e.getMessage());
        }
    }

    public String sendWhatsAppMessage(String toWhatsapp, String messageBody) {
        Message message = Message.creator(new PhoneNumber(toWhatsapp),
                new PhoneNumber(twilioConfig.getFromWhatsappNumber()), messageBody).create();
        return "WhatsApp Sent Successfully : " + message.getSid();
    }

}