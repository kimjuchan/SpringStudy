package com.example.test.demo.config;

import com.example.test.demo.security.service.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailServiceImpl userDetailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // JWT를 파싱하고 인증 정보를 설정하는 로직을 작성
        String token = extractTokenFromRequest(request);

        if (token != null) {
            // 토큰 검증 및 사용자 인증 로직
            Authentication authentication = authenticate(token, request);

            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        // 요청에서 토큰을 추출하는 로직을 작성
        // 실제 구현은 요청 헤더나 쿠키에서 토큰을 추출하는 방식에 따라 다를 수 있음
        return "";
    }

    private Authentication authenticate(String token, HttpServletRequest request) {
        // 토큰 검증 및 사용자 인증 로직을 작성
        // 검증에 실패하면 null을 반환하고, 성공하면 사용자 정보를 포함한 Authentication 객체를 반환

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //test용
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("request.getRequestURI() = " + request.getRequestURI());

        if (username != null && password != null) {
            UserDetails userDetails = userDetailService.loadUserByUsername(username);

            // 비밀번호 확인
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new IllegalAccessError("비밀번호가 일치하지 않습니다.");
            }

            // 인증 객체 생성 및 등록
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            context.setAuthentication(authentication);

            SecurityContextHolder.setContext(context);

            return authentication;
        }
        return null;
    }
}
