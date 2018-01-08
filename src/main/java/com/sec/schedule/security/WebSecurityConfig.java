package com.sec.schedule.security;


import com.sec.schedule.service.SecurityCustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by guoqingyun on 2018/1/3.
 */

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    SecurityCustomUserService securityCustomUserService() {
        return new SecurityCustomUserService();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityCustomUserService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").failureUrl("/login?error").permitAll()
                .and()
                .logout()
                .permitAll();
    }
}
