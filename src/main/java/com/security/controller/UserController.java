package com.security.controller;

import com.security.dto.UserRequest;
import com.security.entity.UserInfo;
import com.security.exception.UserNotFoundException;
import com.security.service.UserInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    private UserInfoService userInfoService;
    public UserController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping("/signup")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<UserInfo> createUser(@RequestBody @Valid UserRequest request){
        return new ResponseEntity<>(userInfoService.saveUser(request), HttpStatus.CREATED);
    }
    @GetMapping("/")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<UserInfo>> getUsers(){
        return  ResponseEntity.ok(userInfoService.getAllUsers());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<UserInfo> getUsers(@PathVariable("id") int id) throws UserNotFoundException {
        return  ResponseEntity.ok(userInfoService.getUserById(id));
    }
    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){
        return ResponseEntity.ok("welcome to security");
    }

}
