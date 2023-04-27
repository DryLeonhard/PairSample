package org.ssafy.sample;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.ssafy.sample.member.MemberService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = SpringSampleApplication.class)
class SpringSampleApplicationTests {

	@Autowired
	private MemberService memberService;
	@BeforeAll
	void beforeTest(){

	}


	@Test
	void testService(){
		assertNotNull(memberService);
	}


}
