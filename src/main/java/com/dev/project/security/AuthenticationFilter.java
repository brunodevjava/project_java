package com.dev.project.security;


import com.auth0.jwt.exceptions.TokenExpiredException;
import com.dev.project.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public AuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws IOException, ServletException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        if (authorizationHeaderIsInvalid(authorizationHeader)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        try {
            SecurityContextHolder.getContext().setAuthentication(parseTokenToUser(authorizationHeader));
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (TokenExpiredException | ExpiredJwtException e) {
            httpServletResponse.setStatus(403);
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpServletResponse.getWriter().append(json(e.getMessage()));
        }catch(Exception e){
            httpServletResponse.setStatus(500);
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().append(json(e.getMessage()));
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    public UsernamePasswordAuthenticationToken parseTokenToUser(String token) {
        Jws<Claims> jwt = jwtService.parseJWT(token.replace("Bearer ", ""));
        List<GrantedAuthority> authorities = new ArrayList<>(1);
        org.springframework.security.core.userdetails.User userFromSpringSecurity =
                new org.springframework.security.core.userdetails.User(
                        jwt.getBody().getSubject(),
                        "",
                        true,
                        true,
                        true,
                        true,
                        authorities);
        return new UsernamePasswordAuthenticationToken(userFromSpringSecurity, null, userFromSpringSecurity.getAuthorities());
    }

    private boolean authorizationHeaderIsInvalid(String authorizationHeader) {
        return authorizationHeader == null
                || !authorizationHeader.startsWith("Bearer ");
    }

    private String json(String message) {
        long date = new Date().getTime();
        return "{\"timestamp\": " + date + ","
                + "\"status\": 401, "
                + "\"error\": \"Unauthorized\", "
                + "\"message\": \"" + message.replace("\"", " ") + "\", "
                + "\"path\": \"/login\"}";
    }
}