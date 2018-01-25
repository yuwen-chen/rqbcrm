package com.crm.manager.member.dto;

import com.crm.manager.common.base.dto.BaseDTO;
import com.crm.manager.member.domain.Member;

public class MemberDTO  extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 437193052447152335L;
	
	private Member member;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
