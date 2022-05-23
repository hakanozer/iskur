package com.works.services;

import com.works.entities.User;
import com.works.repositories.UserRepository;
import com.works.utils.REnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class UserService {

    final UserRepository uRepo;
    public UserService(UserRepository uRepo) {
        this.uRepo = uRepo;
    }

    public ResponseEntity<Map<REnum, Object>> save(User user) {
        // bu bölüm header -> üst bilgi olarak gönderilir.
        HttpHeaders headers = new HttpHeaders();
        headers.add("customData", "123456789");
        User u = uRepo.save(user);

        System.out.println( u.getClass().hashCode() );
        System.out.println( user.getClass().hashCode() );

        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, true);
        hm.put(REnum.result, user);
        return new ResponseEntity<>(hm, headers ,HttpStatus.OK);
    }

}
