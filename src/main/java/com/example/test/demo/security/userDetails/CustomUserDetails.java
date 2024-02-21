package com.example.test.demo.security.userDetails;

import com.example.test.demo.security.domain.Members;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Content : Security에서 제공하는 UserDetails Custom 클래스 작성.
 */
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    //Member 객체
    private final Members members;

    //Member 권한 List
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(members.getRoles()));

        return authorities;
    }

    public Long getId(){
        return members.getId();
    }

    //사용자 pwd
    @Override
    public String getPassword() {
        return members.getPassword();
    }

    //사용자 loginId
    @Override
    public String getUsername() {
        return members.getLoginId();
    }

    //계정 만료되어 있지않을 경우 true 반환
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정 잠겨 있지 않을 경우 true 반환
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //pwd 만료되지 않았을 경우 true 반환.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정 활성화 여부
    @Override
    public boolean isEnabled() {
        return true;
    }
}
