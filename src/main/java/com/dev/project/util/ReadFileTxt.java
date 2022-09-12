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
                if(line.contains("1D")){
                    System.out.println(line.codePoints());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String content = new String(file.getBytes());

        return null;
    }
}
