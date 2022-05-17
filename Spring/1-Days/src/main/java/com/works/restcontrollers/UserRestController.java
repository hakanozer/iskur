package com.works.restcontrollers;

import com.works.entities.User;
import com.works.utils.ERest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserRestController {

    List<User> ls = new ArrayList<>();

    // User Add
    @PostMapping("/userInsert")
    public Map<ERest, Object> insert( @RequestBody User user ) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if ( ls.add(user) ) {
            hm.put(ERest.status, true);
            hm.put(ERest.result, user);
        }else {
            hm.put(ERest.status, false);
            hm.put(ERest.error, "Insert Error");
        }
        return hm;
    }

    // All User
    @GetMapping("/userList")
    public Map<ERest, Object> list() {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status, true);
        hm.put(ERest.result, ls);
        return hm;
    }

    // User Search
    @GetMapping("/userSearch")
    public Map<ERest, Object> userSearch( @RequestParam String q ) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status, false);
        hm.put(ERest.result, null);
        for ( User item : ls ) {
            if ( item.getName().equals(q) ) {
                hm.put(ERest.status, true);
                hm.put(ERest.result, item);
            }
        }
        return hm;
    }


}
