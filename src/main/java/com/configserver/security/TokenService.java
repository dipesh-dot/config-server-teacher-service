package com.configserver.security;

import org.springframework.security.core.Authentication;

public interface TokenService {
     String getTokenByAuth(Authentication authentication);
     Authentication getAuthByToken(String token);
}
