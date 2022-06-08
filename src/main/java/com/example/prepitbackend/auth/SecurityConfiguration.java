package com.example.prepitbackend.auth;

import java.util.Properties;

import com.example.prepitbackend.service.bl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        
        return mailSender;
    }

    @Autowired
    UserService userService;

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Autowired
    private RefreshToken refreshTokenHelper;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManager) throws Exception {
        authenticationManager.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
            .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/auth/register").permitAll()
                .antMatchers("/auth/verify").permitAll()
                .antMatchers("/auth/token/refresh").permitAll()
                .antMatchers("/user/info").authenticated()
                .antMatchers("/user/update-measurements").authenticated()
                .antMatchers("/meal/generate").permitAll()
                .antMatchers("/meal/save").authenticated() // remove
                .antMatchers("/meal/test/plot").permitAll()  // remove
                .antMatchers("/collection/insert").authenticated()   //remove
                .antMatchers("/collection/find-all").authenticated()
                .antMatchers("/collection/find-by-user").authenticated()  //remove
                .antMatchers("/collection/save-meal").authenticated()
                .antMatchers("/collection/find-by-id").authenticated()
                .antMatchers("/v3/api-docs").permitAll()
                .antMatchers("/swagger-ui.html/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated().and()
            .addFilterBefore(new JWTAuthenticationFilter(this.userService, this.jwtTokenHelper, this.refreshTokenHelper), UsernamePasswordAuthenticationFilter.class);

            http.cors();

            http.csrf().disable().headers().frameOptions().disable();
    }

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
    //         .authorizeRequests()
    //             .antMatchers("/auth/login").permitAll()
    //             .antMatchers("/auth/register").permitAll()
    //             .antMatchers("/auth/verify").permitAll()
    //             .antMatchers("/user/info").authenticated()
    //             .antMatchers("/login/oauth2/code/**").authenticated()
    //             .antMatchers("/meal/generate").authenticated()
    //             .antMatchers("/v3/api-docs").permitAll()
    //             .antMatchers("/swagger-ui.html/**").permitAll()
    //             .antMatchers("/swagger-ui/**").permitAll()
    //             .antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated().and()
    //             .oauth2Login().and()
    //             .addFilterBefore(new JWTAuthenticationFilter(this.userService, this.jwtTokenHelper), UsernamePasswordAuthenticationFilter.class);


    //         http.cors();

    //         http.csrf().disable().headers().frameOptions().disable();
    // }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers(HttpMethod.OPTIONS, "/**");
        web.ignoring().mvcMatchers("/swagger-ui.html/**", "/configuration/**", "/swagger-resources/**", "/v2/api-docs","/webjars/**");
    }

}