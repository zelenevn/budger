package com.budger.configurations;

import com.budger.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtFilter filter;

    public SecurityConfiguration(JwtFilter filter) {
        this.filter = filter;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(GET, "/category/*").hasRole("USER")
                .antMatchers(POST, "/category/*").hasRole("USER")
                .antMatchers(GET, "/goal/*").hasRole("USER")
                .antMatchers(POST, "/goal/*").hasRole("USER")
                .antMatchers(POST, "/recommendations/*").hasRole("USER")
                .antMatchers(GET, "/transaction/*").hasRole("USER")
                .antMatchers(POST, "/transaction/*").hasRole("USER")
                .antMatchers(GET, "/report/*").hasRole("USER")
                .antMatchers(POST, "/report/*").hasRole("USER")
                .antMatchers(POST, "/login").anonymous()
                .antMatchers(POST, "/registration").anonymous()
                .and().addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}