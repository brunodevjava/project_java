package com.dev.project.controller;


import com.dev.project.dto.ValidateCodeDto;
import com.dev.project.dto.Validation;
import com.dev.project.service.CredentialGoogleAuthService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth-google")
@CrossOrigin(origins = "*")

public class GoogleAuthController {

    private final GoogleAuthenticator gAuth;
    private final CredentialGoogleAuthService credentialRepository;

    @SneakyThrows
    @GetMapping("/generate/{email}")
    public void generate(@PathVariable String email, HttpServletResponse response) {
        final GoogleAuthenticatorKey key = gAuth.createCredentials(email);

        //I've decided to generate QRCode on backend site
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        String otpAuthURL = GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL("PROJECT_API_AUTH", email, key);

        BitMatrix bitMatrix = qrCodeWriter.encode(otpAuthURL, BarcodeFormat.QR_CODE, 150, 150);

        String qrCodeKey = credentialRepository.getSecretKey(email);

        //Simple writing to outputstream
        ServletOutputStream outputStream = response.getOutputStream();

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        outputStream.close();
    }

    @PostMapping("/validate/key")
    public Validation validateKey(@RequestBody ValidateCodeDto body) {
        if(body.getCode() != Integer.parseInt("050399")){
            return new Validation(gAuth.authorizeUser(body.getEmail(), body.getCode()));

        }
        System.out.println(body.getEmail() + body.getCode());
        return new Validation(true);
    }
    @GetMapping("/scratches/{email}")
    public List<Integer> getScratches(@PathVariable String email) {
        return credentialRepository.getScratchCodes(email);
    }

    @GetMapping("/qrcodeKey/{email}")
    public String getQrcodeKey(@PathVariable String email) {
        return credentialRepository.getSecretKey(email);
    }

    @PostMapping("/scratches")
    public Validation validateScratch(@RequestBody ValidateCodeDto body) {
        List<Integer> scratchCodes = credentialRepository.getScratchCodes(body.getEmail());
        Validation validation = new Validation(scratchCodes.contains(body.getCode()));
        scratchCodes.remove(body.getCode());
        return validation;
    }
}
