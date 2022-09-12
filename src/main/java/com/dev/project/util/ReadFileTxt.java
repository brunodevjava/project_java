package com.dev.project.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Service
public class ReadFileTxt {

    public byte[] ReadFile(MultipartFile file) throws IOException {

        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        //String content = new String(file.getBytes());

        final Charset charset = StandardCharsets.UTF_8;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PrintStream ps = new PrintStream(baos, true, charset.name());

        String line = null;
        try (FileReader fr = new FileReader(convFile);
             BufferedReader br = new BufferedReader(fr);) {
            int index = 1;

            while ((line = br.readLine()) != null) {
//System.out.println("00"+(index++)+"D");

                String in = "00" + index + "D";
                if (!line.contains(in)) {
                    ps.print(line);
                    ps.println();

                    System.out.println(line);

                }
                if (line.contains(in)) {
                    index++;
//                    System.out.println("LINHA PARA MODIFICACAO: "+line);
//                    System.out.println("SALÁRIO CONTRIBUIÇÃO "+line.substring(106, 120));
//                    System.out.println("BASE DE CÁLCULO F.G.T.S. "+line.substring(211, 225));
                    ps.print(line.replace(line.substring(106, 120), line.substring(211, 225)));
                    ps.println();

                    System.out.println(line.replace(line.substring(106, 120), line.substring(211, 225)));
//                    System.out.println("########################");

                }

            }
            String content = new String(baos.toByteArray(), charset);

        } catch (IOException e) {
            e.printStackTrace();
        }
  System.out.println(line);

        return baos.toByteArray();
    }
}
