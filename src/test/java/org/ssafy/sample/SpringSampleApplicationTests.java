package org.ssafy.sample;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.ssafy.sample.member.MemberDao;
import org.ssafy.sample.member.MemberDto;
import org.ssafy.sample.member.MemberService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = SpringSampleApplication.class)
class SpringSampleApplicationTests {

	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberDao memberDao;
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
	void testInsertMember(){
		MemberDto memberDto = MemberDto.memberDtoBuilder()
				.id("testid").nickName("testnickName").password("testPassword").build();
		assertEquals(1,memberDao.saveMemberDto(memberDto));
	}

}
