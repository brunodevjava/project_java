package com.dev.project.controller;


import com.dev.project.dto.*;
import com.dev.project.service.AuthService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public UserTO verifyCredentials(@RequestBody LoginTO loginTO, HttpServletRequest request) {
        try {
            return this.authService.authLogin(loginTO, request);

        } catch (JSONException e) {
            throw new JSONException(e.getCause() + e.getMessage());
        }
    }

    @PostMapping("reset-password")
    public void sendResetPassword(@RequestBody PassResetEmailTO dto) throws MessagingException {
        authService.sendResetPassEmail(dto);
    }

    @PutMapping("/first-access")
    public UserTO firstAccess(@RequestBody LoginTO loginTO) {
        return this.authService.firstAccess(loginTO);
    }

    @PostMapping("/verify-first-access")
    public Map<String, Object> verifyFirstAccess(@RequestBody FirstAccessToken firstAccessToken) {
        Boolean isValid = this.authService.verifyFirstAccessToken(firstAccessToken.getToken());
        HashMap<String, Object> response = new HashMap<>(1);
        response.put("valid", isValid);
        return response;
    }

    @PutMapping("/reset-password")
    public UserTO resetPass(@RequestBody ResetPassTO dto) {
        return authService.resetPass(dto);
    }
}
