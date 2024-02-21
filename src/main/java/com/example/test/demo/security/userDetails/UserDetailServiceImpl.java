package com.example.test.demo.security.userDetails;

import com.example.test.demo.security.domain.Members;
import com.example.test.demo.security.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final MembersRepository membersRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Members members =membersRepository.findByLoginId(loginId).orElseThrow(()-> new UsernameNotFoundException("사용자 정보 없음 :: " + loginId));
        return new CustomUserDetails(members);
    }
}
