package com.gonggu.site.config;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
@Service
public class SecurityService {

    @Value("${jwt.secret_key}")
    String SECRET_KEY;

    @Value("${jwt.expTime}")
    long expTime;

    public String createToken(String subject){
        if(expTime<=0){
            throw new RuntimeException();
        }
        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;
        byte[] secretKeyBytes= DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signatureKey=
                new SecretKeySpec(
                        secretKeyBytes,
                        signatureAlgorithm.getJcaName()
                );
        return Jwts.builder()
                .setSubject(subject)
                .signWith(signatureKey, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis()+expTime))
                .compact();
    }

    public  String getSubject(String tokenBearer){
        String token=tokenBearer.split(" ")[1].trim();

        Claims claims=Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    /**
     * 헤더에서 토큰 가져오는 메소드
     * @param request
     * @return 유저의 id(pk)
     */
    public Integer getIdAtToken(HttpServletRequest request) {
        String tokenBearer = request.getHeader("Authorization");
        String id = getSubject(tokenBearer);
        return Integer.parseInt(id);
    }
}
