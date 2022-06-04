package com.works.restcontrollers;

import com.works.entities.User;
import com.works.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserRestController {

    final UserService uService;
    public UserRestController(UserService uService) {
        this.uService = uService;
    }

    @PostMapping("/save")
    public ResponseEntity save( @Valid @RequestBody User user ){
        return uService.save(user);
    }

    @GetMapping("/list")
    public ResponseEntity list(){
        return uService.userList();
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestParam int  uid){
        return uService.deleteUser(uid);
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@Valid @RequestBody User user){
        return uService.updateUser(user);
    }

    /*
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map handler( MethodArgumentNotValidException ex ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<FieldError> errors = ex.getFieldErrors();
        List<Map<String ,String >> lss = new ArrayList<>();
        for ( FieldError item : errors ) {
            Map<String , String > hmx = new HashMap<>();
            String fieldName = item.getField();
            String message = item.getDefaultMessage();
            hmx.put(fieldName, message);
            System.out.println( fieldName + " " + message );
            lss.add(hmx);
        }
        hm.put(REnum.status, false);
        hm.put(REnum.error, lss);
        return hm;
    }
     */


}
