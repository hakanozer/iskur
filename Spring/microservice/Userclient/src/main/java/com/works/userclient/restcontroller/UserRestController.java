package com.works.userclient.restcontroller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class UserRestController {

    final EurekaClient client;
    public UserRestController(EurekaClient client) {
        this.client = client;
    }

    @GetMapping("/user")
    public Map user() {
        Map<String, Object> hm = new LinkedHashMap<>();

        // client instance
        InstanceInfo info = client
                .getApplication("product_client")
                .getInstances()
                .get(0);
        String host = info.getHostName();

        hm.put("url", host);
        hm.put("user", "Ali Bilmem");
        return hm;
    }

}
