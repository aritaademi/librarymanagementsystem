
package com.example.demo.service;

import com.example.demo.pojo.dto.CreateMemberRequest;
import com.example.demo.pojo.entity.Member;
import com.example.demo.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void updateMember_shouldUpdateNameAndEmail() {
        // Create a member
        Member member = new Member();
        member.setName("Old Name");
        member.setEmail("old@example.com");
        member.setUnpaidPenalty(0);
        member = memberRepository.save(member);

        // Update request
        CreateMemberRequest request = new CreateMemberRequest();
        request.setName("New Name");
        request.setEmail("new@example.com");

        Member updated = memberService.updateMember(member.getId(), request);

        assertEquals("New Name", updated.getName());
        assertEquals("new@example.com", updated.getEmail());
    }
}
