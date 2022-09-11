package com.dev.project.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ReadFileTxt {

    public String ReadFile(MultipartFile file) throws IOException {

        String content = new String(file.getBytes());

        System.out.println(content);
        return content;
    }
}
