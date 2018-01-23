package com.rqb.crm.manager.member.service;

import com.github.pagehelper.PageInfo;
import com.rqb.crm.manager.member.domain.Member;

public interface IMemberService {
	
	public void addMember(Member member);
	
	public PageInfo<Member> queryMember(Integer pageNum,Integer pageSize);
}
