package com.spring.security.yu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    UserDetailsService users() {
        final UserDetails user = User.builder()
                .username("yu").password(passwordEncoder().encode("1234")).roles("USER")
                .build();

        final UserDetails admin = User.builder()
                .username("admin").password(passwordEncoder().encode("1234")).roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    // security에서는 기본적으로 password를 encoding해주지 않으면 오류가 발생한다.
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin(config -> config.loginPage("/login")
//                        .successForwardUrl("/") // 성공시 redirect될 url 명시 가능하지만 requestCache에 저장된 주소로 redirect 되도록 하자.
                        .failureForwardUrl("/login?error=true"))
                .authorizeRequests(config -> config.antMatchers("/login")
                                                    .permitAll()
                                                    .antMatchers("/")
                                                    .authenticated())
                ;
    }
}
