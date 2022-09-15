package com.dev.project.service;

import com.warrenstrange.googleauth.ICredentialRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CredentialGoogleAuthService implements ICredentialRepository {

    private final Map<String, UserTOTP> usersKeys = new HashMap<String, UserTOTP>();

    @Override
    public String getSecretKey(String email) {
        return usersKeys.get(email).getSecretKey();
    }
    public List<Integer> getScratchCodes(@PathVariable String email) {
        return getUser(email).getScratchCodes();
    }

    @Override
    public void saveUserCredentials(String email,
                                    String secretKey,
                                    int validationCode,
                                    List<Integer> scratchCodes) {
        usersKeys.put(email, new UserTOTP(email, secretKey, validationCode, scratchCodes));
    }

    public UserTOTP getUser(String email) {
        return usersKeys.get(email);
    }

    @Data
    @AllArgsConstructor
    class UserTOTP {
        private String email;
        private String secretKey;
        private int validationCode;
        private List<Integer> scratchCodes;
    }
}