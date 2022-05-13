package com.example.prepitbackend.auth;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenHelper {

    @Value("${jwt.auth.app}")
    private String application;

    @Value("${jwt.auth.secret_key}")
    private String secretKey;

    @Value("${jwt.auth.expires_in}")
    private int expiresIn;
    
    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    private Claims getAllClaims(String token) {
        Claims  claims;
        try{
            claims = Jwts.parser()
                    .setSigningKey(this.secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public String getUsernameFromToken(String token) {
        String username;
        try{
            Claims claims = this.getAllClaims(token);
            username = claims.getSubject();
        } catch (Exception e){
            username = null;
        }
        return username;
    }

    private Date getExpirationDateFromToken(String token){
        Date date;
        try{
            Claims claims = this.getAllClaims(token);
            date = claims.getExpiration();
        } catch (Exception e){
            date = null;
        }
        return date;
    }

    private boolean isTokenExpired(String token){
        Date expireDate = this.getExpirationDateFromToken(token);
        return expireDate.before(new Date());
    }

    public Date getIssuedAtFromToken(String token){
        Date date;
        try{
            Claims claims = this.getAllClaims(token);
            date = claims.getIssuedAt();
        } catch (Exception e){
            date = null;
        }
        return date;
    }

    private String getAuthHeaderFromRequest(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public String getToken(HttpServletRequest request){
        String authHeader = getAuthHeaderFromRequest(request);
        if (authHeader != null && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);
        }
        return null;
    }


    public String generateToken(String username) throws InvalidKeySpecException, NoSuchAlgorithmException{
        return Jwts.builder()
                    .setIssuer(this.application)
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + this.expiresIn * 1000))
                    .signWith(this.SIGNATURE_ALGORITHM, this.secretKey)
                    .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        String username = this.getUsernameFromToken(token);
        return (username != null && username.equals(userDetails.getUsername()) && !this.isTokenExpired(token));
    }


}
