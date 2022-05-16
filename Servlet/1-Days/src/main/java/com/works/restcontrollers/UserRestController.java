package com.works.restcontrollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @GetMapping("")
    public String user() {
        return "Ali Bilmem";
    }

}
