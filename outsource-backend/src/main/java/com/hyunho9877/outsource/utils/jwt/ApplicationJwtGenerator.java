package com.hyunho9877.outsource.utils.jwt;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface ApplicationJwtGenerator {
    String generateAccessToken(String subject, String issuer, Collection<GrantedAuthority> authorities);
    String generateAccessToken(String subject, String issuer);
    String generateAccessToken(String subject, int expiration, String issuer);
    String generateRefreshToken(String subject, String issuer);
    String generateRefreshToken(String subject, int expiration, String issuer);
    long getAccessTokenExpiration();
    long getRefreshTokenExpiration();
}
