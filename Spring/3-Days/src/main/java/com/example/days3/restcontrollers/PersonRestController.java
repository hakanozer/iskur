package com.example.days3.restcontrollers;

import com.example.days3.services.UtilService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class PersonRestController {

    final UtilService uService;
    public PersonRestController(UtilService uService) {
        this.uService = uService;
    }

    @GetMapping("/sum")
    public Map<String, Object> sum( int a, int b ) {
        Map<String, Object> hm = new LinkedHashMap<>();
        int sm = uService.sum(a, b);
        hm.put("Sum", sm);
        return hm;
    }


}
