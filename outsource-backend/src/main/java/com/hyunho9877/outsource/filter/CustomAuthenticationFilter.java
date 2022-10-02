package com.hyunho9877.outsource.filter;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.hyunho9877.outsource.config.JwtConfig;
import com.hyunho9877.outsource.domain.ApplicationUser;
import com.hyunho9877.outsource.repo.ApplicationUserRepository;
import com.hyunho9877.outsource.utils.jwt.ApplicationJwtGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtConfig jwtConfig;
    private final ApplicationUserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final ApplicationJwtGenerator jwtGenerator;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("id");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        String accessToken = jwtGenerator.generateAccessToken(user.getUsername(), request.getRequestURL().toString(), user.getAuthorities());
        String refreshToken = jwtGenerator.generateRefreshToken(user.getUsername(), request.getRequestURL().toString());

        ApplicationUser applicationUser = ApplicationUser.getPublicProfile(userRepository.findById(user.getUsername()).orElseThrow(), false);
        log.info("user nickname : {}", applicationUser.getNickName());
        response.setHeader(jwtConfig.getAccessTokenHeader(), accessToken);
        response.setHeader(jwtConfig.getRefreshTokenHeader(), refreshToken);
        response.getWriter().write(new Gson().toJson(applicationUser));
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
