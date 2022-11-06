package com.example.springsecurityproject.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    private  PrivateKey key;
    private  JwtParser jwtParser;

    private String BASE_64_SECRET = "RjKP2Lae6Q/o4YN0BVh7/QdwUYp0LxfujuqE+tt46EB7yVR37ln9uLm/oanDpjVnXTvhdaLunPFMLzuM6JYaJV/ASAfA1RJxMfnEadsIvjr+PyVDIEP5qfw9et8Sw7WYpOoMcAOyTXHLcKh8jP8XkMJXnRNH1DpQRoYx/rbChRU+iU3FbpJQIryrf6gvseOIuofxgfy6lnTkK/8yIJy4axYvmI1NbcHeJdpS9A==";


    public TokenProvider() {
//        byte[] keyBytes = Decoders.BASE64.decode(BASE_64_SECRET);
        BASE_64_SECRET = Base64.getEncoder().encodeToString(BASE_64_SECRET.getBytes());
//
//        try{
//            PKCS8EncodedKeySpec formattedPrivate = new PKCS8EncodedKeySpec(keyBytes);
//            KeyFactory kf = KeyFactory.getInstance("DSA");
//
//            this.key = kf.generatePrivate(formattedPrivate);
//            this.jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
//        }catch (NoSuchAlgorithmException ex){
//            System.out.printf("Algo : " + ex.getMessage());
//            this.key = null;
//            this.jwtParser =null;
//        } catch (InvalidKeySpecException exception) {
//            System.out.printf("INvalid : " + exception.getMessage());
//            this.key = null;
//            this.jwtParser =null;
//        }
    }

    public String createToken(final Authentication authentication){

        final String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors
                        .joining(","));

        final long currentTime = new Date().getTime();
        final Date tokenValidity = new Date(currentTime+1000*2592000L);

        return Jwts
                .builder()
                .setSubject(authentication.getName())
                .claim("auth",authorities)
                .signWith(SignatureAlgorithm.HS256, BASE_64_SECRET)
                .setExpiration(tokenValidity).compact();
    }

    public Authentication getAuthentication(final String token){
//        final Claims claims = jwtParser.parseClaimsJws(token).getBody();
        final Claims claims = Jwts.parser().setSigningKey(BASE_64_SECRET).parseClaimsJws(token).getBody();

        Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get("auth").toString().split(","))
                .filter(auth -> !auth.trim().isEmpty())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User user = new User(claims.getSubject(),"",authorities);

        return  new UsernamePasswordAuthenticationToken(user,token,authorities);

    }

    public boolean validateToken(final String token){

        try{
            jwtParser.parseClaimsJws(token);
            return true;
        }catch (Exception ex){
            //TODO
        }

        return false;
    }
}
