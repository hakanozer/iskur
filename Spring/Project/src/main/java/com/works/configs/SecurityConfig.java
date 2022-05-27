package com.works.configs;

import com.works.services.JWTUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final JwtFilter jwtFilter;
    final JWTUserDetailService jwtUserDetailService;
    public SecurityConfig(JwtFilter jwtFilter, JWTUserDetailService jwtUserDetailService) {
        this.jwtFilter = jwtFilter;
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
        http
                .authorizeRequests() // giriş rolleri ile çalış
                .antMatchers("/register", "/auth").permitAll()
                .antMatchers("/product/**").hasRole("user")
                .antMatchers("/note/**").hasRole("admin")
                .antMatchers("/sales/**").hasRole("customer")
                .and()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class );

    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /*
    urunler -> user
    note -> admin
    sales -> customer

    erkan@mail.com -> user
    serkan@mail.com -> admin
    ayse@mail.com -> customer
    zehra@mail.com -> user, admin

     */
}
