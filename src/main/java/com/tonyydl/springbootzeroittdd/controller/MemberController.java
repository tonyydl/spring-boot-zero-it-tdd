package com.tonyydl.springbootzeroittdd.controller;

import com.tonyydl.springbootzeroittdd.data.dto.MemberDto;
import com.tonyydl.springbootzeroittdd.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> getMember(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(memberService.getMember(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping
    public ResponseEntity<MemberDto> updateMember(@RequestBody MemberDto memberDto) {
        return ResponseEntity.ok(memberService.updateMember(memberDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
