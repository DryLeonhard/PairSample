package org.ssafy.sample.member;

import org.springframework.stereotype.Service;
import org.ssafy.sample.exception.NotValidUserException;

@Service
public class MemberService {
    private final MemberDao memberDao;

    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void createMember(MemberDto memberDto){
        memberDao.createMember(memberDto);
    }

    public MemberDto isValidMember(MemberDto memberDto) throws NotValidUserException {
        MemberDto findMember = memberDao.findMember(memberDto.getId());
        if(findMember == null) throw new NullPointerException("없는 아이디");
        if(memberDto.getPassword().equals(findMember.getPassword())){
            return findMember;
        }else{
            throw new NotValidUserException("없거나 비밀번호 틀림");
        }
    }
}
