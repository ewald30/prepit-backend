package com.example.prepitbackend;

import java.util.Arrays;

import com.example.prepitbackend.service.bl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManager) throws Exception {
        authenticationManager.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    // @Bean
    // public AuthenticationProvider authProvider(){
    //     DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    //     provider.setUserDetailsService(userService);
    //     provider.setPasswordEncoder(new BCryptPasswordEncoder());
    //     return provider;
    // }

    // @Override
    // public void configure(HttpSecurity http) throws Exception {
    //    http.authorizeRequests()
    //     .antMatchers("/").permitAll()
    //     .antMatchers(HttpMethod.POST,"/newuser").permitAll()
    //     .antMatchers(HttpMethod.POST, "/login").permitAll()
    //     .antMatchers(HttpMethod.POST,"/newuser/*").permitAll()
    //     .antMatchers(HttpMethod.GET,"/user/*").permitAll()
    //      .antMatchers(HttpMethod.GET,"/exploreCourse").permitAll()
    //     .anyRequest().authenticated();
    //     http.cors().and().csrf().disable();
    // }

    // @Bean
    // CorsConfigurationSource corsConfigurationSource() {
    //     CorsConfiguration configuration = new CorsConfiguration();
    //     configuration.setAllowedOrigins(Arrays.asList("*"));
    //     configuration.setAllowedMethods(Arrays.asList("*"));
    //     configuration.setAllowedHeaders(Arrays.asList("*"));
    //     configuration.setAllowCredentials(true);
    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     source.registerCorsConfiguration("/**", configuration);
    //     return source;
    // }
}