package com.notification_service.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class SmsRequest {

    private String toNumber;
    private String message;
}
