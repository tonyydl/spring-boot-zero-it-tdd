package com.tonyydl.springbootzeroittdd.controller;

import com.tonyydl.springbootzeroittdd.data.dto.MemberDto;
import com.tonyydl.springbootzeroittdd.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto memberDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberService.createMember(memberDto));
    }
}
