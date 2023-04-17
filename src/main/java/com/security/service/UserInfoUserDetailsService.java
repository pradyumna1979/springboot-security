package com.security.service;

import com.security.config.UserInfoUserDetails;
import com.security.entity.UserInfo;
import com.security.exception.UserNotFoundException;
import com.security.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo>  userInfo = userInfoRepository.findByName(username);
        try {
            return    userInfo.map(UserInfoUserDetails::new).orElseThrow(()->new UserNotFoundException("User Not found "+username));
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
