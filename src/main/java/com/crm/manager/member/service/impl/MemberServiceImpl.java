package com.crm.manager.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.manager.member.dao.IMemberDao;
import com.crm.manager.member.dto.MemberDTO;
import com.crm.manager.member.service.IMemberService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class MemberServiceImpl implements IMemberService{
	
	@Autowired
	IMemberDao 	memberDao;

	@Override
	public void addMember(MemberDTO member) {
		memberDao.insertMember(member);
	}

	@Override
	public PageInfo<MemberDTO> queryMember(MemberDTO memberDTO) {
		if(memberDTO == null){
			return null;
		}
		PageHelper.startPage(memberDTO.getPageNumber(), memberDTO.getPageSize());  
		return new PageInfo<MemberDTO>(memberDao.queryMember(memberDTO));
	}
}
