package com.security.controller;

import com.security.dto.UserRequest;
import com.security.entity.User;
import com.security.exception.UserNotFoundException;
import com.security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserRequest request){
        return new ResponseEntity<>(userService.saveUser(request), HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers(){
        return  ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUsers(@PathVariable("id") int id) throws UserNotFoundException {
        return  ResponseEntity.ok(userService.getUserById(id));
    }
    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){
        return ResponseEntity.ok("welcome to security");
    }

}
