package com.crm.manager.member.service;

import com.crm.manager.member.domain.Member;
import com.crm.manager.member.dto.MemberDTO;
import com.github.pagehelper.PageInfo;

public interface IMemberService {
	
	public void addMember(Member member);
	
	public PageInfo<Member> queryMember(MemberDTO memberDTO);
}
