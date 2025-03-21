package com.accounts.accounts.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY="tGa2nbDpGEbG8UAgXvaGOLHZQ4aFZ1cBZP5zRMiQzbbdd22gi6wlIiDNEnktwIO5yzceKUa8Gd0VzkZk14LvhCfOmrqTbCrgRdOzLQeAuwhv8Yk8jc4vKfbzhJe7WzGnLZGvkG2MHsE6PiWq6kUC7XY60+xRL0y78nvBDWZIM/I7VMky33k2UxkSn0PPq4OS3wjJzZ8ySTiUWk1h8CthWb4r3BiEY1fEjBTlhbEj+u+ZGiZZZtd1k/R1uD6DzSdXoT2sJuTZYkXcouuQlZ9UZJU9IFnfK73XyC0xjTup6ZJUTFe9t5bJcznmJXzxf4EjqNMADE+cG18j95XOAyp89dGpvt5IXB+yuKFuziOs5sc=\n";
    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }
    public <T>T extractClaims(String token, Function<Claims,T> claimsResolver){
        final Claims claims =extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    //    public String generateToken(UserDetails userDetails){
//        return generateToken(new HashMap<>(),userDetails);
//        final String username=extractUsername(token);
//        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
//    }
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username =extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    private Date extractExpiration(String token){
        return extractClaims(token,Claims::getExpiration);
    }
    public String generateToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private SecretKey getSigningKey(){
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

}

