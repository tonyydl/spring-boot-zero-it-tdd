package com.tonyydl.springbootzeroittdd.controller;

import com.tonyydl.springbootzeroittdd.data.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    @PostMapping
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto memberDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MemberDto
                .builder()
                .id(1L)
                .firstName(memberDto.getFirstName())
                .lastName(memberDto.getLastName())
                .age(memberDto.getAge())
                .height(memberDto.getHeight())
                .build());
    }
}
