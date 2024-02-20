package com.example.test.demo.security.service;

import com.example.test.demo.security.domain.Members;
import com.example.test.demo.security.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final MembersRepository membersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Members members =membersRepository.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("해당 사용자 정보 없음"));
        return (UserDetails) members;
    }
}
