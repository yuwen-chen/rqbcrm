package com.test.member;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rqb.crm.manager.Application;
import com.rqb.crm.manager.member.dao.IMemberDao;
import com.rqb.crm.manager.member.domain.Member;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MemberTest {
	
	@Autowired
	IMemberDao memberMapper;
	
	@Test
	public void TestAdd(){
		Member member = new Member();
		//member.setId(111L);
		member.setName("test");
		member.setPhone("175664");
		member.setMail("136@qq.com");
		memberMapper.insertMember(member);
	}
	
	@Test
	public void TestQuery(){
		//分页插件: 查询第1页，每页10行 
	    PageHelper.startPage(1, 2);  
	    PageInfo<Member> page =new PageInfo<Member>(memberMapper.queryMember());
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
