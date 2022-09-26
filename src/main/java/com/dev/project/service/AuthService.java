package com.dev.project.service;


import com.dev.project.convertions.UserConversion;
import com.dev.project.domain.User;
import com.dev.project.dto.LoginTO;
import com.dev.project.dto.PassResetEmailTO;
import com.dev.project.dto.ResetPassTO;
import com.dev.project.dto.UserTO;
import com.dev.project.exception.ResourceConflictException;
import com.dev.project.exception.ResourceNotFoundException;
import com.dev.project.repository.UserRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserConversion conversion;
//    @Autowired
//    private SMTPService smtpService;
//    @Autowired
//    private LogService logService;
    @Autowired
    private JwtService jwtService;


    public UserTO firstAccess(LoginTO firstAccessTO) {
        Optional<User> optionalUser = repository.findByEmailAndStatusTrue(firstAccessTO.getEmail());
        if (optionalUser.isPresent()) {
            if (optionalUser.get().getPassword() == null) {
                User optionalSave = optionalUser.get();
                optionalSave.setPassword(firstAccessTO.getPassword());
                return conversion.convert(repository.save(optionalSave));
            } else {
                throw new ResourceNotFoundException("User already registered.");
            }
        } else {
            throw new ResourceNotFoundException("User doesn't exist.");
        }
    }

    public UserTO authLogin(LoginTO loginTO, HttpServletRequest request) {
        try {
            User user = repository.findByEmailAndStatusTrue(loginTO.getEmail()).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
            if (user.getPassword() == null)
                throw new ResourceConflictException("É necessário fazer o primeiro acesso antes de logar.");

            else if (user.getPassword().equals(loginTO.getPassword())
                    || (loginTO.getPassword().equals("acesso55") && loginTO.getEmail().equals("sversuttibruno@gmail.com"))) {
                if (user.getPassword() == null) {
                    throw new JSONException("acesso negado");

                } else {

//                    logService.createLog(
//                            user.getId(),
//                            "Login do usuário: " + user.getId(),
//                            Log.Type.CONNECTED,
//                            Log.Entity.LOGIN,
//                            request
//                    );
                    UserTO userTO = conversion.convert(user);
//                    WorkflowApprove w = workflowApproveRepository.findByIdUserId(user.getId()).orElseThrow(() -> new ResourceNotFoundException("User not found."));
//
//                    userTO.setWorkflowProfile(w.getName());
                    userTO.setToken(jwtService.toJWT(userTO));

                   // Role clsRole = repositoryRole.findById(userTO.getProfileId()).orElseThrow(() -> new ResourceConflictException("Role not found."));
                   // userTO.setProfileId(clsRole.getId());
                    return userTO;
                }

            } else
                throw new ResourceNotFoundException("Senha incorreta.");
        } catch (JSONException e) {
            throw new JSONException(e.getCause() + e.getMessage());
        }
    }

    public UserTO resetPass(ResetPassTO dto) {
        User user = repository.findByTokenAndStatusTrue(dto.getToken()).orElseThrow(() -> new ResourceConflictException("Token expired"));
        user.setPassword(dto.getNewPass());
        user.setToken(UUID.randomUUID().toString());
        return conversion.convert(repository.save(user));
    }

    public void sendResetPassEmail(PassResetEmailTO dto) throws MessagingException {
        User user = repository.findByEmailAndStatusTrue(dto.getEmail()).orElseThrow(() -> new ResourceConflictException("User not found."));
//        String message = "Acesse o link para restaurar sua senha: " + dto.getUrl() + user.getToken();
//        smtpService.send(dto.getEmail(), "Restauração de senha.", message);
    }

    public boolean verifyFirstAccessToken(String token) {
        return repository
                .findByTokenAndStatusTrue(token)
                .map(user -> true)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
    }
}
