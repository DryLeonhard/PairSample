package org.ssafy.sample.member;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @PostMapping("/members/register")
    public ResponseEntity postMember(@RequestBody MemberDto memberDto){
        System.out.println(memberDto.toString());
        memberService.createMember(memberDto);
        return new ResponseEntity<>(memberDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody MemberDto memberDto, HttpSession session){
        System.out.println(memberDto.toString());
        MemberDto findMember = memberService.isValidMember(memberDto);
        if(findMember!=null){
            session.setAttribute("MemberDto",findMember);

            return new ResponseEntity<>(findMember,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Login Failed",HttpStatus.UNAUTHORIZED);
        }
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
