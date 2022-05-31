package com.works.services;

import com.works.entities.User;
import com.works.repositories.UserJoinRepository;
import com.works.repositories.UserRepository;
import com.works.utils.REnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    final UserRepository uRepo;
    final UserJoinRepository uJoinRepo;
    public UserService(UserRepository uRepo, UserJoinRepository uJoinRepo) {
        this.uJoinRepo = uJoinRepo;
        System.out.println("UserService Call");
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


    //list
    public ResponseEntity<Map<String ,Object>>  userList(){

        Map<REnum,Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status,true);
        hm.put(REnum.result, uRepo.findAll());
        hm.put( REnum.error, uJoinRepo.userJoin(1) );

        return new  ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity<Map<String ,Object>> deleteUser(int  uid ){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        try {
            uRepo.deleteById(uid);
            hm.put(REnum.status,true);
            return new  ResponseEntity(hm, HttpStatus.OK);
        }catch (Exception ex) {
            hm.put(REnum.status,false);
            hm.put(REnum.message, ex.getMessage());
            return new  ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Map<String ,Object>> updateUser(User user){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        try{
            Optional<User> oUser = uRepo.findById(null);//burada 2.gun degişiklik yapıldı
            if(oUser.isPresent()){
                uRepo.saveAndFlush(user);
                hm.put(REnum.result, user);
                hm.put(REnum.status, true);
            }else{
                hm.put(REnum.status, false);
            }
        }catch (Exception e){
            hm.put(REnum.status, false);
            hm.put(REnum.message, e.getMessage());
        }
        return new  ResponseEntity(hm, HttpStatus.OK);
    }


}
