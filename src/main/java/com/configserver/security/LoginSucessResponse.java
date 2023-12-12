package com.configserver.security;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginSucessResponse {

    private boolean success;
    private  String token;
    private String name;
}
