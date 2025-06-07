package com.example.MyUserService.Controller;

import com.example.MyUserService.Services.Impl.UserServiceImpl;
import com.example.MyUserService.Services.UserService;
import com.example.MyUserService.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.createUser(user);
//        return new ResponseEntity<>(user1, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //get user by user id
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId){
        User userById = userService.getUserById(userId);
        return ResponseEntity.ok(userById);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    @GetMapping("/hello")
    public String Hello(){
        return  "hello";
    }

}
