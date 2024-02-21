package com.example.test.demo.security.jwt;


import com.example.test.demo.security.userDetails.CustomUserDetails;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
import com.auth0.jwt.JWT;

import javax.naming.ldap.PagedResultsControl;

@Component
@Getter
public class JwtUtil {

    /**
     * 일단 JWT 정보 관련해서 YML 파일에 설정 후 가져오는 방식으로 진행.
     *
     */

    private String secret = "secret";

    private String expirationTime = "120000";

    //TOKEN 값 PREFIX 임시로 설정 (테스트용)
    private String TOKEN_PREFIX = "Bearer";
    private String HEADER_STRING = "Authorization";


    //Create Token
    public String createAuthJwtToken(CustomUserDetails userDetails) {
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                //CustomUserDetails 객체는 entity id 값이 없어서 getId 추가
                .withClaim("id", userDetails.getId())
                .withClaim("username", userDetails.getUsername())
                //임시로 secret 값 설정.
                .sign(Algorithm.HMAC512(secret));
    }

    //해당 Token 정보에서 claim 정보만 추출
    public String decodeJwtToken(String jwtToken, String secretKey, String claim) {
        return JWT.require(Algorithm.HMAC512(secretKey)).build()
                .verify(jwtToken)
                .getClaim(claim)
                .asString();
    }

    //Token 값에 "Bearer" 선언된  prefix  자름.
    public String extractBearer(String jwtHeader) {
        int pos = jwtHeader.lastIndexOf(" ");
        return jwtHeader.substring(pos + 1);
    }

}
