package com.dev.project.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class ReadFileTxt {

    public String ReadFile(MultipartFile file) throws IOException {

        File convFile = new File( file.getOriginalFilename() );
        FileOutputStream fos = new FileOutputStream( convFile );
        fos.write( file.getBytes() );
        fos.close();
        //String content = new String(file.getBytes());

        try (FileReader fr = new FileReader(convFile);
             BufferedReader br = new BufferedReader(fr);) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(br.readLine().contains("000000000000000000004400000000030562000000000002444900000000030705000000000003557700000000027147300000000026655000000000027804600000000030562001"));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String content = new String(file.getBytes());

        return null;
    }
}
