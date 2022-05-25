package com.works.services;

import com.works.entities.JWTUser;
import com.works.repositories.JWTUserRepository;
import com.works.utils.REnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class JWTUserDetailService implements UserDetailsService {

    final JWTUserRepository jwtUserRepository;
    public JWTUserDetailService(JWTUserRepository jwtUserRepository) {
        this.jwtUserRepository = jwtUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
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

}
