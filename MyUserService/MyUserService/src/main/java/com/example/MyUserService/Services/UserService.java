package com.example.MyUserService.Services;

import com.example.MyUserService.entities.User;

import java.util.List;

public interface UserService {
     User createUser(User user);
     List<User> getAllUser();
     User getUserById(String userId);

}
