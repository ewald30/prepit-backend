package com.example.prepitbackend.auth;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class RefreshToken {
    
    @Value("${refresh.auth.app}")
    private String application;

    @Value("${refresh.auth.secret_key}")
    private String secretKey;

    @Value("${refresh.auth.expires_in}")
    private int expiresIn;

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

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

    public boolean isTokenExpired(String token){
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

    private String getRefreshTokenFromRequest(HttpServletRequest request) {
        return request.getHeader("Refresh-Token");
    }

    public String getToken(HttpServletRequest request){
        String authHeader = getRefreshTokenFromRequest(request);
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

    public boolean validateToken(String token){
        String username = this.getUsernameFromToken(token);
        return (username != null && !this.isTokenExpired(token));
    }

    public String refreshToken(HttpServletRequest request, String username) throws InvalidKeySpecException, NoSuchAlgorithmException{
        String jwtToken = this.jwtTokenHelper.getToken(request);
        String refreshToken = this.getToken(request);

        if (jwtToken != null && validateToken(refreshToken)){
            return jwtTokenHelper.generateToken(username);
        }

        return null;
    }


}

