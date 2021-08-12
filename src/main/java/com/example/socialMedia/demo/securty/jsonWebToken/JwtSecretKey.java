package com.example.socialMedia.demo.securty.jsonWebToken;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtSecretKey {

    private  JwtConfig jwtConfig = new JwtConfig() ;

    @Autowired
    public JwtSecretKey( ) {

        this.jwtConfig.setSecretKey("securesecuresecuresecure" +
                "securesecuresecure" +
                "securesecuresecure" +
                "securesecuresecure" +
                "securesecuresecure" +
                "securesecuresecure" +
                "securesecure");
        this.jwtConfig.setTokenPrefix("Bearer ");
        this.jwtConfig.setTokenExpirationAfterDays(14);
    }


    @Bean
    public SecretKey getSecretKey()
    {
        return Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
    }
}
