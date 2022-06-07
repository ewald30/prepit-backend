package com.example.prepitbackend.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.prepitbackend.service.bl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter{

    private UserService userService;

    private JWTTokenHelper tokenHelper;

    private RefreshToken refreshTokenHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String refreshToken = refreshTokenHandler.getToken(request);
        
        if (refreshToken != null && refreshTokenHandler.validateToken(refreshToken)){
            String authToken = tokenHelper.getToken(request);

            if (authToken != null) {
                String username = tokenHelper.getUsernameFromToken(authToken);
    
                if (username != null) {
                    UserDetails userDetails = this.userService.loadUserByUsername(username);
    
                    if (tokenHelper.validateToken(authToken, userDetails)){
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            }
        }

        filterChain.doFilter(request, response);
        
    }

}