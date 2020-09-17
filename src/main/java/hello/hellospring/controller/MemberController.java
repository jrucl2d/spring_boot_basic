package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;

    // Controller는 따로 설정이 불가능하므로 Autowired를 써야 함
    @Autowired // Controller와 Service를 연결
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
