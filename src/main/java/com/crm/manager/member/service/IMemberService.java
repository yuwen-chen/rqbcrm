package com.crm.manager.member.service;

import com.crm.manager.common.exception.BusinessException;
import com.crm.manager.member.dto.MemberDTO;
import com.github.pagehelper.PageInfo;

public interface IMemberService {
	
	/**
	 * 新增会员
	 * @param memberDTO
	 * @return
	 */
	public void addMember(MemberDTO memberDTO)throws BusinessException ;
	
	/**
	 * 通过ID查询会员
	 * @param id
	 * @return
	 */
	public MemberDTO queryMemberById(String memberTable, String id)throws BusinessException ;
	
	/**
	 * 更新会员
	 * @param memberDTO
	 * @return
	 */
	public void updateMember(MemberDTO memberDTO)throws BusinessException ;
	
	/**
	 * 通过ID删除会员
	 * @param memberDTO
	 * @return
	 */
	public void removeMemberById(MemberDTO memberDTO)throws BusinessException ;
	
	/**
	 * 分页查询会员
	 * @param memberDTO
	 * @return
	 */
	public PageInfo<MemberDTO> queryMember(MemberDTO memberDTO)throws BusinessException ;
}
