package org.ssafy.sample.member;

import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberDao memberDao;

    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void createMember(MemberDto memberDto){
        memberDao.createMember(memberDto);
    }

    public MemberDto isValidMember(MemberDto memberDto){
        MemberDto findMember = memberDao.findMember(memberDto.getId());
        if(memberDto.getPassword().equals(findMember.getPassword())){
            return findMember;
        }else{
            return null;
        }
    }
}
