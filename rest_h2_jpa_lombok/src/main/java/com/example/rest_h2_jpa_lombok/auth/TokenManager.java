package com.example.rest_h2_jpa_lombok.auth;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;


import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenManager {
    private final JwtEncoder jwtEncoder;


    //private static final String secretKey = "04b14fe49864c4b5b4f5d5b";
    private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long JWT_EXPIRATION = 70000;

    //Token üret
    public String generateToken(Authentication authentication){

        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime()+ JWT_EXPIRATION);
        return Jwts.builder()

                .setSubject(username) //usernamei ayarlar
                .setIssuedAt(new Date())//hangi tarihte imzalandı
                .setExpiration(expireDate) //ne zamana kadar geçerli
                .signWith(secretKey)
                .compact();

    }

    //tokenın doğruluğunu yanlışlığını kontrol eder.
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT was exprired or incorrect",ex.fillInStackTrace());
        }
    }

    public String getUserNameToken(String token){
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }


}

