/*
package com.example.test.demo.config;

import com.example.test.demo.security.domain.Members;
import com.example.test.demo.security.jwt.JwtUtil;
import com.example.test.demo.security.repository.MembersRepository;
import com.example.test.demo.security.userDetails.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;


public class JwtAuthoriztionFilter extends BasicAuthenticationFilter {

    //
    private final MembersRepository membersRepository;
    private final JwtUtil jwtUtil;

    public JwtAuthoriztionFilter(AuthenticationManager authenticationManager , MembersRepository membersRepository,
                                 JwtUtil jwtUtil) {
        //BasicAuthenticationFilter는 AuthenticationManager를 사용하기 때문에 super를 사용해서 주입해준다.
        super(authenticationManager);
        this.membersRepository = membersRepository;
        this.jwtUtil = jwtUtil;
    }

    //API 요청으로 들어온 토큰 값을 기반으로 사용자 인가 처리
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String jwtHeader = request.getHeader(jwtUtil.getHEADER_STRING());

        if (jwtHeader == null || !jwtHeader.startsWith(jwtUtil.getTOKEN_PREFIX())) {
            chain.doFilter(request, response);
            return;
        }

        //header에 담긴 토큰 정보를 추출해서 loginId 정보 추출
        String jwtToken = jwtUtil.extractBearer(jwtHeader);
        String loginId = jwtUtil.decodeJwtToken(jwtToken, jwtUtil.getSecret(), "loginId");


        //loginId 정보 기준으로 db상에 계정이 있는지 확인 후  Authentication 객체에 계정정보 저장하여, SecurityContext에 객체 저장. 그 이후 필터 진행.
        if (loginId != null) {
            Members members = membersRepository.findByLoginId(loginId)
                    .orElseThrow(() -> new UsernameNotFoundException("해당 username을 갖는 계정을 찾을 수 없습니다."));

            CustomUserDetails customUserDetails = new CustomUserDetails(members);
            Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetails, null,
                    customUserDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

}


*/
