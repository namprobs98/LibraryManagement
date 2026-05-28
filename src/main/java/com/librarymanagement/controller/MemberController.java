package com.librarymanagement.controller;

import com.librarymanagement.entity.Member;
import com.librarymanagement.service.MemberService;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    public boolean addMember(String id, String name, String email, String phone) {
        return memberService.addMember(new Member(id, name, email, phone));
    }

    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    public Optional<Member> getMemberById(String id) {
        return memberService.getMemberById(id);
    }

    public boolean updateMember(String id, String name, String email, String phone) {
        return memberService.updateMember(id, name, email, phone);
    }

    public boolean deleteMember(String id) {
        return memberService.deleteMember(id);
    }
}
