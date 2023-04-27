package org.ssafy.sample;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.ssafy.sample.exception.NotValidUserException;
import org.ssafy.sample.member.MemberController;
import org.ssafy.sample.member.MemberDao;
import org.ssafy.sample.member.MemberDto;
import org.ssafy.sample.member.MemberService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = SpringSampleApplication.class)
class SpringSampleApplicationTests {

	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MemberController memberController;

	@BeforeAll
	static void beforeTest(){

	}


	@Test
	void testService(){
		assertNotNull(memberService);
	}
	@Test
	void testDao(){
		assertNotNull(memberDao);
	}
	@Test
	void testController() {assertNotNull(memberController);}

	@Test
	void testInsertMember(){
		MemberDto memberDto = MemberDto.memberDtoBuilder()
				.id("testid").nickName("testnickName").password("testPassword").build();
		assertEquals(1,memberDao.saveMemberDto(memberDto));
	}



	@Test
	void testLogin() throws NotValidUserException {
		MemberDto memberDto = MemberDto.memberDtoBuilder()
				.id("testid").password("testPassword").build();

		MemberDto toFind = MemberDto.memberDtoBuilder()
				.id("testid").nickName("testnickName").password("testPassword").build();

		assertEquals(toFind.getId(),memberService.isValidMember(memberDto).getId());
		assertEquals(toFind.getPassword(),memberService.isValidMember(memberDto).getPassword());
		assertEquals(toFind.getNickName(),memberService.isValidMember(memberDto).getNickName());
	}

	@Test
	void testDeleteMember(){
		assertEquals(1,memberDao.deleteMemberById("testid"));
		assertNull(memberDao.findMemberById("testid"));
	}
}
