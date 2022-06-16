package com.onur.controllers;

import com.onur.entities.Message;
import com.onur.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/message")
@CrossOrigin(origins = "http://localhost:3000")
public class MessageRestController {


    final MessageService mService;
    public MessageRestController(MessageService mService) {
        this.mService = mService;
    }

    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody Message message) {
        return mService.save(message);
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        return mService.list();
    }

    @GetMapping("/read/{id}")
    public ResponseEntity read( @PathVariable Long id  ) {
        return mService.read(id);
    }



}
