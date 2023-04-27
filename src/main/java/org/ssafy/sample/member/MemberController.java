package org.ssafy.sample.member;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ssafy.sample.exception.NotValidUserException;


@RestController
@RequestMapping("/api")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @PostMapping("/members/register")
    public ResponseEntity postMember(@RequestBody MemberDto memberDto){
        System.out.println(memberDto.toString());
        memberService.createMember(memberDto);
        return new ResponseEntity<>(memberDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody MemberDto memberDto, HttpSession session) throws NotValidUserException {
        System.out.println(memberDto.toString());
        MemberDto findMember = memberService.isValidMember(memberDto);

        session.setAttribute("MemberDto", findMember);
        log.debug(findMember.toString());
        return new ResponseEntity<>(findMember, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpSession session){
        session.invalidate();
        return new ResponseEntity<>("Logout",HttpStatus.OK);
    }


    @GetMapping("/login.do")
    public ResponseEntity checkLogon(HttpSession session){
        MemberDto memberDto = (MemberDto) session.getAttribute("MemberDto");
        if(null!=memberDto) return new ResponseEntity<>(memberDto,HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
