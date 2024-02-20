package com.example.test.demo.config;

import com.example.test.demo.ApiDto.ApiErrorResponse;
import com.example.test.demo.security.service.UserDetailServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.io.PrintWriter;
import java.util.HashMap;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    /**
     * 참조 : https://velog.io/@woosim34/Spring-Spring-Security-%EC%84%A4%EC%A0%95-%EB%B0%8F-%EA%B5%AC%ED%98%84SessionSpring-boot3.0-%EC%9D%B4%EC%83%81
     *
     *
     */

    private final UserDetailServiceImpl userDetailService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //임시로 모든 권한 열어둠
        http
                .authorizeHttpRequests(
                        auth ->
                                auth
                                        .anyRequest().authenticated()
                )
                .httpBasic(HttpBasicConfigurer::disable)
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(FormLoginConfigurer::disable)
                .userDetailsService(userDetailService)
                .logout(withDefaults())
                .exceptionHandling((exceptionConfig) ->
                        exceptionConfig.authenticationEntryPoint(unauthorizedEntryPoint).accessDeniedHandler(accessDeniedHandler)
                                  ) // 401 403 관련 예외처리
                //UsernamePasswordAuthenticationFilter 보다 먼저 실행됨.
                //UsernamePasswordAuthenticationFilter.class를 통해서 userNm,pwd 인증처리 전에 custom환경에서 JWT 토큰 검증 필터 추가방식.
                /**
                 * 인증 과정
                 * 클라이언트로부터 인증 요청이 들어옵니다.
                 * UsernamePasswordAuthenticationFilter가 요청을 가로채어 사용자가 입력한 인증 정보를 확인합니다.
                 * AuthenticationManager를 통해 사용자 인증을 수행합니다.
                 * 인증이 성공하면 Authentication 객체가 생성됩니다.
                 * Authentication 객체는 SecurityContext저장 후 이친구는 또 SecurityContextHolder에 저장되어 현재 인증된 사용자 정보를 관리합니다.
                 * 요청은 다음 필터로 계속 전달됩니다.
                 */
                //.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        ;
        return http.build();
    }

    //Authentication에 대한 예외처리
    private final AuthenticationEntryPoint unauthorizedEntryPoint =
            (request, response, authException) -> {
                ApiErrorResponse fail = new ApiErrorResponse(HttpStatus.UNAUTHORIZED, "401 : Unauthorized");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                String json = new ObjectMapper().writeValueAsString(fail);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                PrintWriter writer = response.getWriter();
                writer.write(json);
                writer.flush();
            };

    //Authorization에 대한 예외처리
    private final AccessDeniedHandler accessDeniedHandler =
            (request, response, accessDeniedException) -> {
                ApiErrorResponse fail = new ApiErrorResponse(HttpStatus.FORBIDDEN, "403 : Forbidden");
                response.setStatus(HttpStatus.FORBIDDEN.value());
                String json = new ObjectMapper().writeValueAsString(fail);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                PrintWriter writer = response.getWriter();
                writer.write(json);
                writer.flush();
            };


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) ->
                web.ignoring()
                        .requestMatchers("/resources/**")
                        .requestMatchers("/static/**")
                ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * HEADER : 토큰 타입 , 서명에 사용할 알고리즘.
     * PAYLOAD : 인증을 위해 사용할 실제 정보들 (클레임)
     * 서명(Signature) = 인코딩 헤더 + 인코딩 비밀키 + 비밀 키 + 헤더의 알고리즘
     */

    /**
     * 헤더 값을 생성해주는 메서드
     * @return HashMap<String, Object> 헤더 값
     */

    /**
     * DelegatingFilterProxy
     *
     *
     * @return
     */
    private static HashMap<String, Object> createHeader() {
        HashMap<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());
        return header;
    }



}
