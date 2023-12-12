package com.configserver.security;


import com.usermanagementservice.utils.SecurityUtilsConstant;
import lombok.*;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthToken {
    private Authentication authentication;
    private  String token;
    private LocalDateTime dateTime;


    public boolean isExpired(){
        return dateTime.plusSeconds(SecurityUtilsConstant.EXPIRE_TIME/1000).isBefore(LocalDateTime.now());
    }
    public void renew(){
        dateTime = LocalDateTime.now();
    }
}
