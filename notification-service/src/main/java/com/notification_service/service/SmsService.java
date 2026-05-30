package com.notification_service.service;

import com.notification_service.config.TwilioConfig;
import com.notification_service.dto.SmsRequest;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SmsService {
    private final TwilioConfig twilioConfig;

    public String sendSms(SmsRequest request) {

        try {
            Message message = Message.creator(new PhoneNumber(request.getToNumber()),
                    new PhoneNumber(twilioConfig.getPhoneNumber()), request.getMessage()).create();
            return "SMS Sent Successfully. SID : " + message.getSid();
        } catch (Exception e) {
            throw new RuntimeException("Failed to send SMS : " + e.getMessage());
        }
    }

    public String sendSms(String to, String messageBody) {
        Message message = Message.creator(new PhoneNumber(to),
                new PhoneNumber(twilioConfig.getPhoneNumber()), messageBody).create();
        return "SMS Sent Successfully. SID : " + message.getSid();
    }
}
