package clinicManagement.jwt;

import clinicManagement.entity.UserEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JwtTokenService {
    @Value("${jwt.expiration}")
    private int expiration;
    @Value("${jwt.secret.key}")
    private String secretKey;

    public String generateToken(UserEntity userEntity){
       List<String> roles = userEntity.getAuthorities().stream()
               .map(GrantedAuthority::getAuthority)
               .toList();
        return Jwts
                .builder()
                .subject(userEntity.getEmail())
                .claim("roles", roles)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSignKey())
                .compact();
    }

    public Jws<Claims> extractToken(String token){
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseSignedClaims(token);
    }



    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
