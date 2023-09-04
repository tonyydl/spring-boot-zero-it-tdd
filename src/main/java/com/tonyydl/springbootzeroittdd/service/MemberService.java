package com.tonyydl.springbootzeroittdd.service;

import com.tonyydl.springbootzeroittdd.data.dto.MemberDto;
import com.tonyydl.springbootzeroittdd.repository.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberDto createMember(MemberDto memberDto) {
        return memberRepository.save(memberDto.toPo()).toDto();
    }
}
