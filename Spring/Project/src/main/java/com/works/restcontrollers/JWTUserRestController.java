package com.works.restcontrollers;

import com.works.entities.JWTUser;
import com.works.services.JWTUserDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTUserRestController {

    final JWTUserDetailService jwtUserDetailService;
    public JWTUserRestController(JWTUserDetailService jwtUserDetailService) {
        this.jwtUserDetailService = jwtUserDetailService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody JWTUser jwtUser) {
        return jwtUserDetailService.register(jwtUser);
    }


}
