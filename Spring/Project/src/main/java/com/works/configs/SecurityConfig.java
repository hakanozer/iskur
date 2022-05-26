package com.works.configs;

import com.works.services.JWTUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final JWTUserDetailService jwtUserDetailService;
    public SecurityConfig(JWTUserDetailService jwtUserDetailService) {
        this.jwtUserDetailService = jwtUserDetailService;
    }

    // veritabanında yönetim, kullanıcı varlık denetimi
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailService).passwordEncoder( jwtUserDetailService.encoder() );
    }

    // role ve yönetim
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().formLogin().disable();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
