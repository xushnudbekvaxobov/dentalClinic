package clinicManagement.jwt;

import clinicManagement.dto.responseDto.JwtDto;
import clinicManagement.entity.UserEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        return Jwts
                .builder()
                .subject(userEntity.getEmail())
                .claim("roles", userEntity.getAuthorities())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSignKey())
                .compact();
    }

    public JwtDto extractToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String username = claims.getSubject();
        List<SimpleGrantedAuthority> roles = claims.get("roles", List.class);
        return new JwtDto(username, roles);
    }



    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
