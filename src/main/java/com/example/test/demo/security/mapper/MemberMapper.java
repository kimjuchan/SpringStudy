package com.example.test.demo.security.mapper;


import com.example.test.demo.security.domain.Members;
import com.example.test.demo.security.dto.MemberCreateRequest;
import com.example.test.demo.security.dto.MembersDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

//Mapping 하지 않은 값에 대한 오류 넘김 처리를 위해서 IGNORE 처리.
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

    //Mapper 설정.
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    //MemberCreateRequest TO MembersDto Type 변환.
    Members MemberCreateRequestToEntity(MemberCreateRequest memberCreateRequest);

    //MembersDto TO Members Entity Type 변환
    Members MembersDtoToMembers(MembersDto membersDto);
    
    //Member TO MembersDto Type 변환
    MembersDto MembersToMembersDto(Members members);
    
    
    
}
