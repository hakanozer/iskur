package com.works.restcontrollers;

import com.works.entities.User;
import com.works.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {

    final UserService uService;
    public UserRestController(UserService uService) {
        this.uService = uService;
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody User user){
        return uService.save(user);
    }


}
