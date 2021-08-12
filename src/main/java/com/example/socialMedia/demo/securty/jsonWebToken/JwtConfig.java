package com.example.socialMedia.demo.securty.jsonWebToken;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;

@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {

    private String secretKey = "securesecuresecuresecure" +
            "securesecuresecuresecure" +
            "securesecuresecuresecure" +
            "securesecuresecuresecure" +
            "securesecuresecuresecure" +
            "secure";
    private String tokenPrefix = "Bearer ";
    private Integer tokenExpirationAfterDays = 14;

    public JwtConfig() {

    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public Integer getTokenExpirationAfterDays() {
        return tokenExpirationAfterDays;
    }

    public void setTokenExpirationAfterDays(Integer tokenExpirationAfterDays) {
        this.tokenExpirationAfterDays = tokenExpirationAfterDays;
    }


    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }

}
