package com.security.service;

import com.security.dto.UserRequest;
import com.security.entity.UserInfo;
import com.security.exception.UserNotFoundException;
import com.security.repository.UserInfoRepository;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public UserInfo saveUser(UserRequest userRequest) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(userRequest.getName());
        userInfo.setEmail(userRequest.getEmail());
        userInfo.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userInfo.setRoles(userRequest.getRoles());
        return userInfoRepository.save(userInfo);
    }

    public List<UserInfo> saveUsers(List<UserRequest> userRequests) {
        List<UserInfo> userInfos = userRequests.stream()
                .map(userRequest -> {
                    UserInfo userInfo = new UserInfo();
                    userInfo.setName(userRequest.getName());
                    userInfo.setEmail(userRequest.getEmail());
                    userInfo.setPassword(passwordEncoder.encode(userRequest.getPassword()));
                    userInfo.setRoles(userRequest.getRoles());
                    return userInfo;
                }).collect(Collectors.toList());

        return userInfoRepository.saveAll(userInfos);
    }

    public List<UserInfo> getAllUsers() {
        return userInfoRepository.findAll();
    }

    public UserInfo getUserById(Integer id) throws UserNotFoundException {
        return userInfoRepository.findById(id).orElseThrow(()->new UserNotFoundException("User Not found id: " + id));

    }
    public void uploadFile(MultipartFile file) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet xssfSheet=workbook.getSheetAt(0);
        XSSFRow xssfRow=xssfSheet.getRow(0);

    }
}
