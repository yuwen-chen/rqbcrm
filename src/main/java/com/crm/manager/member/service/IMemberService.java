package com.crm.manager.member.service;

import com.crm.manager.member.dto.MemberDTO;
import com.github.pagehelper.PageInfo;

public interface IMemberService {
	
	public void addMember(MemberDTO memberDTO);
	
	public PageInfo<MemberDTO> queryMember(MemberDTO memberDTO);
}
