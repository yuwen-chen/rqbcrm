package com.rqb.crm.manager.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rqb.crm.manager.member.dao.IMemberDao;
import com.rqb.crm.manager.member.domain.Member;
import com.rqb.crm.manager.member.service.IMemberService;

@Service
public class MemberServiceImpl implements IMemberService{
	
	@Autowired
	IMemberDao 	memberDao;

	@Override
	public void addMember(Member member) {
		memberDao.insertMember(member);
	}

	@Override
	public PageInfo<Member> queryMember(Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);  
		PageInfo<Member> page=new PageInfo<Member>(memberDao.queryMember());
		return page;
	}
}
