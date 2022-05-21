package com.example.days3.restcontrollers;

import com.example.days3.services.UtilService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class PersonRestController {

    final UtilService uService;
    public PersonRestController(UtilService uService) {
        this.uService = uService;
    }

    @GetMapping("/sum/{a}/{b}")
    public Map<String, Object> sum( @PathVariable(name = "a") int ax, @PathVariable int b ) {
        Map<String, Object> hm = new LinkedHashMap<>();
        int sm = uService.sum(ax, b);
        hm.put("Sum", sm);
        return hm;
    }


}

// @RequestBody -> Json nesnesini entity formata serileştirme için
// Validation api içerisindeki özellikleri kullanmak için @RequestBody ile data alımı önerilir.

// @RequestParam -> Post yöntemi ile gelen form elemanlarının datasını yakalamk için kullanılır.
// @PathVariable -> Get yöntemi ile url bölümünde valueları "/" ile ayırarak almak istediğimizde kullanacağımız yöntemdir.
// QueryString -> = ? işareti ile key=val yöntemi ile veri alımı için kullanılır. Herhangi bir notasyona gerek yoktur.