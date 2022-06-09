package com.works.productclient.restcontrollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductRestController {

    @GetMapping("/product")
    public Map user() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("product", "product");

        String url = "https://jsonplaceholder.typicode.com/posts";
        RestTemplate template = new RestTemplate();
        List<User> data = template.getForObject( url, List.class );
        hm.put("data", data);

        return hm;
    }

}
