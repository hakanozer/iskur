package com.works.services;

import com.works.configs.JwtUtil;
import com.works.entities.JWTLogin;
import com.works.entities.JWTUser;
import com.works.entities.Role;
import com.works.repositories.JWTUserRepository;
import com.works.utils.REnum;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class JWTUserDetailService implements UserDetailsService {

    // Token üretmek için bu nesne altındaki generator fonnksiyonu kullanılır.
    final JwtUtil jwtUtil;
    final JWTUserRepository jwtUserRepository;
    // Spring Securitye haber vermek için ara sınıf olarak kullanılacak.
    final AuthenticationManager authenticationManager;
    public JWTUserDetailService(JwtUtil jwtUtil, JWTUserRepository jwtUserRepository, @Lazy AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.jwtUserRepository = jwtUserRepository;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<JWTUser> optionalJWTUser = jwtUserRepository.findByEmailEqualsIgnoreCase(username);
        if (optionalJWTUser.isPresent()) {
            JWTUser u = optionalJWTUser.get();
            UserDetails userDetails = new User(
                    u.getEmail(),
                    u.getPassword(),
                    u.isEnabled(),
                    u.isTokenExpired(),
                    true,
                    true,
                    roles(u.getRoles())
            );
            return userDetails;
        }else {
            throw new UsernameNotFoundException("Böyle bir kullanıcı yok");
        }
    }


    public Collection roles( List<Role> rolex ) {
        List<GrantedAuthority> ls = new ArrayList<>();
        for ( Role role : rolex ) {
            ls.add( new SimpleGrantedAuthority( role.getName() ));
        }
        return ls;
    }

    public ResponseEntity register(JWTUser jwtUser) {
        Optional<JWTUser> optionalJWTUser = jwtUserRepository.findByEmailEqualsIgnoreCase(jwtUser.getEmail());
        Map<REnum, Object> hm = new LinkedHashMap();
        if ( !optionalJWTUser.isPresent() ) {
            jwtUser.setPassword(  encoder().encode( jwtUser.getPassword() )  );
            JWTUser user = jwtUserRepository.save(jwtUser);
            hm.put(REnum.status, true);
            hm.put(REnum.result, user);
            return new ResponseEntity( hm , HttpStatus.OK);
        }else {
            hm.put(REnum.status, false);
            hm.put(REnum.message, "Bu mail daha kayıt edilmiş");
            hm.put(REnum.result, jwtUser);
            return new ResponseEntity( hm, HttpStatus.NOT_ACCEPTABLE );
        }
    }

    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    // aut
    // jwt almak için login işlemi yaparak bu fonksiyon tetiklenmeldir.
    public ResponseEntity auth(JWTLogin jwtLogin) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                    jwtLogin.getUsername(), jwtLogin.getPassword()
            ) );
            UserDetails userDetails = loadUserByUsername(jwtLogin.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);
            hm.put(REnum.status, true);
            hm.put( REnum.jwt, jwt );
            return new ResponseEntity(hm, HttpStatus.OK);
        }catch (Exception ex) {
            hm.put(REnum.status, false);
            hm.put( REnum.error, ex.getMessage() );
            return new ResponseEntity(hm, HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
