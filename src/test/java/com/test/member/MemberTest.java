package com.test.member;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crm.manager.Application;
import com.crm.manager.member.dao.IMemberDao;
import com.crm.manager.member.domain.Member;
import com.crm.manager.member.enums.MemberTableEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MemberTest {
	
	@Autowired
	IMemberDao memberMapper;
	
	@Test
	public void TestAdd(){
		for(int i=1;i<=10;i++){
			Member member = new Member();
			member.setId("UID000"+i);
			member.setPhone("13526455623");
			member.setRealName("张三"+i);
			member.setIndentityType("01");
			member.setIndentityNo("315645894522244");
			member.setSex(1);
			member.setAddress("上海");
			member.setUserStatus("01");
			member.setEmail("123@qq.com");
			member.setAppPlatform("C");
			member.setFinancialLevel(1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
			member.setRegisterDate(sdf.format(new Date()));
			member.setMemberTable("t_b_member");
			memberMapper.insertMember(member);
		}
	}
	
	@Test
	public void TestQuery(){
		//分页插件: 查询第1页，每页10行 
	    PageHelper.startPage(1, 10);  
	    Member member = new Member();
	    member.setMemberTable(MemberTableEnum.T_B_MEMBER.getName());
	    member.setId("UID0001");
		member.setPhone("13526455623");
		member.setRealName("张三1");
		member.setIndentityType("01");
		member.setIndentityNo("315645894522244");
		member.setSex(1);
		member.setAddress("上海");
		member.setUserStatus("01");
		member.setEmail("123@qq.com");
		//member.setAppPlatform(MemberTableEnum.T_C_MEMBER.getType());
		member.setFinancialLevel(1);
	    PageInfo<Member> page =new PageInfo<Member>(memberMapper.queryMember(member));
		List<Member> list = page.getList();
		for(Member mem : list){
			System.out.println(mem);
		}
		page.getPageNum();
		System.out.println(page.getPageNum());
		System.out.println(page.getPageSize());
		System.out.println(page.getPages());
		
	}

}
