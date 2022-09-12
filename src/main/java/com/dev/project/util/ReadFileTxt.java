package com.dev.project.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class ReadFileTxt {

    public String ReadFile(MultipartFile file) throws IOException {

        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        //String content = new String(file.getBytes());

        try (FileReader fr = new FileReader(convFile);
             BufferedReader br = new BufferedReader(fr);) {
            String line;
            int index = 1;
            while ((line = br.readLine()) != null) {
//System.out.println("00"+(index++)+"D");

                String in = "00"+index+"D";
                if(!line.contains(in)){
//                    System.out.println("Linha pulada");

                }
                if (line.contains(in)) {
                    index++;
                    System.out.println("LINHA PARA MODIFICACAO: "+line);
                    System.out.println("SALÁRIO CONTRIBUIÇÃO "+line.substring(106, 120));
                    System.out.println("BASE DE CÁLCULO F.G.T.S. "+line.substring(211, 225));
                    System.out.println(line.replaceAll(line.substring(106, 120), line.substring(211, 225)));
                    System.out.println("########################");

                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String content = new String(file.getBytes());

        return null;
    }
}
