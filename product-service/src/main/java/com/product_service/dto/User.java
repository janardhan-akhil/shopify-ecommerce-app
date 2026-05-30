package com.product_service.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class User{

    private long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String role;
}
