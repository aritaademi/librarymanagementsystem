package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.pojo.dto.CreateMemberRequest;
import com.example.demo.pojo.entity.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Integer id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));
    }


    public Member updateMember(Integer id, CreateMemberRequest request) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        member.setName(request.getName());
        member.setEmail(request.getEmail());

        return memberRepository.save(member);
    }


    public void deleteMember(Integer id) {
        Member member = getMemberById(id);
        memberRepository.delete(member);
    }
}
