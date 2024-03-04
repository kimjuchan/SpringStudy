package com.example.test.demo.config;


import com.example.test.demo.security.jwt.JwtUtil;
import com.example.test.demo.security.userDetails.CustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;


/**
 *  Spring Bean으로 등록하지 않는 이유
 *  -> 해당 클래스가 AuthenticationManager를 의존성 주입받는데 해당 클래스를 사용하는 SecurityConfig에서 AuthenticationManager를 빈으로 등록하기 때문에 순환 참조가 발생하기 때문이다.
 *
 *
 *
 */

@RequiredArgsConstructor
public class JwtAuthenticationFilters extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    //@SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();

        //request 정보 받아와 CustomUserDetails 타입 객체 생성.
        CustomUserDetails customUserDetails = null;
        try {
            customUserDetails = objectMapper.readValue(request.getInputStream(), CustomUserDetails.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(customUserDetails.getUsername(), customUserDetails.getPassword());

        //Authentication authentication = authenticationManager.authenticate(authenticationToken);
        return getAuthenticationManager().authenticate(authenticationToken);

    }

    //인증 완료 후 TOKEN 생성
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        CustomUserDetails customUserDetails = (CustomUserDetails) authResult.getPrincipal();
        //Token 발급.
        String jwtToken = jwtUtil.createAuthJwtToken(customUserDetails);
        response.addHeader(jwtUtil.getHEADER_STRING(), jwtUtil.getTOKEN_PREFIX() + " " + jwtToken);
    }



}
