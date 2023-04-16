package com.security.service;

import com.security.entity.User;
import com.security.dto.UserRequest;
import com.security.exception.UserNotFoundException;
import com.security.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(UserRequest userRequest) {
        User user = new User();
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setGender(userRequest.getGender());
        user.setMobile(userRequest.getMobile());
        user.setNationality(userRequest.getNationality());
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int userId) throws UserNotFoundException {
        User user = userRepository.findAllByUserId(userId);
        if(user == null)
            throw new UserNotFoundException("User Not found id: "+userId);
            return user;
    }
}
