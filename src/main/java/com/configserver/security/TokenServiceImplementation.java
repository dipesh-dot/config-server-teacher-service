package com.configserver.security;


import com.usermanagementservice.utils.SecurityUtilsConstant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImplementation implements TokenService{

    Map<String ,AuthToken>map = new HashMap<>();


    public AuthToken generateNewToken(String username){
        AuthToken authToken = new AuthToken();
        authToken.setDateTime(LocalDateTime.now());
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(SecurityUtilsConstant.EXPIRE_TIME+ System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512,SecurityUtilsConstant.SECRET)
                .compact();
        authToken.setToken(token);
    return authToken;
    }

    @Override
    public String getTokenByAuth(Authentication authentication) {
        // 1. Get the username from the Authentication object
        String token = authentication.getName();
        // 2. Generate a new authentication token using the username
        AuthToken authToken= generateNewToken(token);
        // 3. Set the Authentication object in the AuthToken
        authToken.setAuthentication(authentication);
        // 4. Put the generated AuthToken into a map with its token as the key
        map.put(authToken.getToken(),authToken);
        // 5. Return the generated token
        return authToken.getToken();
    }

    @Override
    public Authentication getAuthByToken(String token) {

        AuthToken authToken = map.getOrDefault(token,new AuthToken());

        return authToken.isExpired()? authToken.getAuthentication():null;
    }
}
