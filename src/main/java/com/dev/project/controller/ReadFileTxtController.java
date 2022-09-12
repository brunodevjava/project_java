package com.dev.project.controller;


import com.dev.project.dto.AddressTO;
import com.dev.project.service.AddressService;
import com.dev.project.util.ReadFileTxt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

@RestController
@RequestMapping(value = "/txt")
@CrossOrigin(origins = "*")
public class ReadFileTxtController {

    @Autowired
    private ReadFileTxt service;

    @PostMapping
    public byte[] save(@RequestPart(value = "file") MultipartFile file) throws IOException {
        return service.ReadFile(file);
    }

}
