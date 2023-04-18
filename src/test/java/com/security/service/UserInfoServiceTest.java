package com.security.service;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static  org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.*;

@SpringBootTest
public class UserInfoServiceTest {
    MockMultipartFile file;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    String fileName;
    FileInputStream fileInputStream;
    @InjectMocks
    UserInfoService userInfoService;

    @DisplayName("Test MultipartFile")
    @Test
    public void uploadFileTest() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File filePath = new File(classLoader.getResource("test.xlsx").getFile());
        fileName ="test.xlsx";
        fileInputStream = new FileInputStream(filePath);
        workbook = new XSSFWorkbook();
        file = new MockMultipartFile(fileName, fileInputStream);

        userInfoService.uploadFile(file);
    }
}
