package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.pojo.dto.CreateMemberRequest;
import com.example.demo.pojo.entity.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    MemberRepository  memberRepository;

    @PostMapping
    public Member createMember(@Valid @RequestBody CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setUnpaidPenalty(0.0); // default value

        return memberRepository.save(member);
    }


    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public Member getMember(@PathVariable Integer id) {
        return memberService.getMemberById(id);
    }


    @PutMapping("/{id}")
    public Member updateMember(@PathVariable Integer id, @Valid @RequestBody CreateMemberRequest request) {
        return memberService.updateMember(id, request);
    }


    @DeleteMapping("/{id}")

    public void deleteMember(@PathVariable Integer id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));
        memberRepository.delete(member);
    }
}
