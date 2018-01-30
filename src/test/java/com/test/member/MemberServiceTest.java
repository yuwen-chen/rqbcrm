package com.test.member;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crm.manager.Application;
import com.crm.manager.member.dto.MemberDTO;
import com.crm.manager.member.enums.MemberTableEnum;
import com.crm.manager.member.service.IMemberService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MemberServiceTest {
	
	@Autowired
	IMemberService memberService;
	
	@Test
	public void TestQuery(){
		
		MemberDTO member = new MemberDTO();
		member.setPageNumber(1);
		member.setPageSize(10);
		member.setMemberTable(MemberTableEnum.T_A_MEMBER.getName());
		memberService.queryMember(member);
	}

}
