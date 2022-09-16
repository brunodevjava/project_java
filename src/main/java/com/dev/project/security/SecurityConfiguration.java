package com.dev.project.security;

import com.dev.project.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtService jwtService;

    private static final RequestMatcher PROTECTED_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/data/**"),
            new AntPathRequestMatcher("/auth-google/**"),
            new AntPathRequestMatcher("/address/**"),
            new AntPathRequestMatcher("/txt/**")
    );

    @Override
    public void configure(final WebSecurity webSecurity) {
        webSecurity.ignoring()
                .antMatchers("/h2-console/**")
                .antMatchers("/auth/**");
    }

    /*
     PROTECAO HTTP COM FILTROS DE AUTENTICACAO JWT TOKEN,
     ADD DE CORS, CRIACAO DE POLITICA DE PRIVACIDADE E PROTECAO
     CONTRA SQL INJECTION;
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new AuthenticationFilter(jwtService), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .requestMatchers(PROTECTED_URLS).authenticated()
                .and()
                .csrf().disable()
                .cors().and()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable();
    }


}