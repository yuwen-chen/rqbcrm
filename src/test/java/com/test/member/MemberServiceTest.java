package com.test.member;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crm.manager.Application;
import com.crm.manager.member.service.IMemberService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MemberServiceTest {
	
	@Autowired
	IMemberService memberService;
	
	@Test
	public void TestQuery(){
		//memberService.queryMember(1, 3);
	}

}
