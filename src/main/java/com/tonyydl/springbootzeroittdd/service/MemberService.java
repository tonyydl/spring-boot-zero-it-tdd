package com.tonyydl.springbootzeroittdd.service;

import com.tonyydl.springbootzeroittdd.data.dto.MemberDto;
import com.tonyydl.springbootzeroittdd.data.po.MemberPo;
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

    public MemberDto getMember(Long id) {
        return memberRepository
                .findById(id)
                .map(MemberPo::toDto)
                .orElseThrow(RuntimeException::new);
    }

    public MemberDto updateMember(MemberDto memberDto) {
        MemberPo memberPo = memberRepository
                .findById(memberDto.getId())
                .orElseThrow(RuntimeException::new);

        memberPo.setAge(memberDto.getAge());
        memberPo.setHeight(memberDto.getHeight());
        return memberRepository.save(memberPo).toDto();
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
