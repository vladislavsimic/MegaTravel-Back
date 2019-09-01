package com.xml.megatravel.config.security;

import com.xml.megatravel.config.CustomProperties;
import com.xml.megatravel.exception.CustomException;
import com.xml.megatravel.model.User;
import com.xml.megatravel.model.enumeration.Role;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    private final CustomProperties customProperties;

    public JwtTokenUtil(CustomProperties customProperties) {
        this.customProperties = customProperties;
    }

    public String generateAuthToken(Authentication authentication) {
        User userPrincipal = (User) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + customProperties.getJwtExpirationInMs());
        Claims claims = Jwts.claims().setSubject(userPrincipal.getId().toString());
        claims.put("role", userPrincipal.getAuthorities()
                .stream()
                .findFirst()
                .orElseThrow(() -> new CustomException("Role not found!"))
                .getAuthority());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, customProperties.getJwtSecret())
                .compact();
    }

    public UUID getUserIdFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(customProperties.getJwtSecret())
                .parseClaimsJws(token)
                .getBody();

        return UUID.fromString(claims.getSubject());
    }

    public Role getRoleFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(customProperties.getJwtSecret())
                .parseClaimsJws(token)
                .getBody();

        return Role.valueOf((String)claims.get("role"));
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(customProperties.getJwtSecret()).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}
