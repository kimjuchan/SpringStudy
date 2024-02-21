package com.example.test.demo.security.service;

import com.example.test.demo.security.domain.Members;
import com.example.test.demo.security.dto.MemberCreateRequest;
import com.example.test.demo.security.mapper.MemberMapper;
import com.example.test.demo.security.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MembersServiceImpl implements MembersService{

    private final MembersRepository membersRepository;
    private final PasswordEncoder passwordEncoder;

    //Members Create
    public int membersCreate(MemberCreateRequest memberCreateRequest){

        log.info("-----mem create start-----");
        memberCreateRequest.setPassword(passwordEncoder.encode(memberCreateRequest.getPassword()));
        //param 형변환
        Members members = MemberMapper.INSTANCE.MemberCreateRequestToEntity(memberCreateRequest);
        log.info("-----MemberCreateRequest to Members Entity change-----");
        //member save
        membersRepository.save(members);
        log.info("-----mem create end-----");
        return 1;
    }



}
