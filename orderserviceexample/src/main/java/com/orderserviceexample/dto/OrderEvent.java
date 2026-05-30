package com.orderserviceexample.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class OrderEvent {

    private Long orderId;
    private String email;
    private String mobile;
    private String toWhatsapp;
    private String status;
}
