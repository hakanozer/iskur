package com.onur.services;

import com.onur.entities.Message;
import com.onur.repositories.MessageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class MessageService {


    final MessageRepository mRepo;
    public MessageService(MessageRepository mRepo) {
        this.mRepo = mRepo;
    }


    public ResponseEntity save(Message message) {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("result", mRepo.save(message) );
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity list() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("result", mRepo.findAll() );
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity read( Long id ) {
        Map<String, Object> hm = new LinkedHashMap<>();
        Optional<Message> optionalMessage = mRepo.findById(id);
        if (optionalMessage.isPresent() ) {
            Message m = optionalMessage.get();
            m.setRead(true);
            hm.put("status", true);
            hm.put("result", mRepo.saveAndFlush(m) );
        }else {
            hm.put("status", false);
        }
        return new ResponseEntity(hm, HttpStatus.OK);
    }

}
