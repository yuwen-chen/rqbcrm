package com.crm.manager.member.service;

import com.crm.manager.member.dto.MemberCodeDTO;
import com.github.pagehelper.PageInfo;

public interface IMemberCodeService {
	
	/**
	 * 新增会员编码
	 * @param memberDTO
	 * @return
	 */
	public boolean addMemberCode(MemberCodeDTO memberCodeDTO);
	
	/**
	 * 更新会员编码
	 * @param memberDTO
	 * @return
	 */
	public boolean updateMemberCode(MemberCodeDTO memberCodeDTO);
	
	/**
	 * 通过ID删除会员编码
	 * @param memberDTO
	 * @return
	 */
	public boolean removeMemberCodeByMemberId(MemberCodeDTO memberCodeDTO);
	
	/**
	 * 分页查询会员
	 * @param memberDTO
	 * @return
	 */
	public PageInfo<MemberCodeDTO> queryMemberCode(MemberCodeDTO memberCodeDTO);
}
