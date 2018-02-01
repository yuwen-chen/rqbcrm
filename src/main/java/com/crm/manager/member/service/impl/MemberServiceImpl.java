package com.crm.manager.member.service.impl;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.manager.member.dao.IMemberDao;
import com.crm.manager.member.dto.MemberDTO;
import com.crm.manager.member.enums.MemberTableEnum;
import com.crm.manager.member.service.IMemberService;
import com.crm.manager.staff.dto.StaffAllotRecordsDTO;
import com.crm.manager.staff.dto.StaffDTO;
import com.crm.manager.staff.service.IStaffAllotRecordsService;
import com.crm.manager.staff.service.IStaffService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class MemberServiceImpl implements IMemberService{
	
	@Autowired
	private IMemberDao 	memberDao;
	
	@Autowired
	private IStaffService staffService;
	
	@Autowired
	private IStaffAllotRecordsService staffAllotRecordsService;

	@Override
	@Transactional
	public boolean addMember(MemberDTO member) {
		if(member == null || StringUtils.isBlank(member.getId())){
			return false;
		}
		//查询会员人数最少的工作人员编号进行分配
		StaffDTO staff = staffService.queryMinMemberNumStaff();
		if(staff == null || StringUtils.isBlank(staff.getStaffNo())){
			return false;
		}
		member.setStaffNo(staff.getStaffNo());
		StaffAllotRecordsDTO staffAllotRecordsDTO = new StaffAllotRecordsDTO();
		//新增分配记录
		staffAllotRecordsDTO.setStaffNo(staff.getStaffNo());
		staffAllotRecordsDTO.setMemberId(member.getId());
		boolean result = staffAllotRecordsService.addStaffAllotRecords(staffAllotRecordsDTO);
		if(result){
			result = memberDao.insertMember(member) > 0;
		}
		return result;
	}
	
	@Override
	public MemberDTO queryMemberById(String appPlatform, String id) {
		if(StringUtils.isBlank(appPlatform) ||  StringUtils.isBlank(id)){
			return null;
		}
		String memberTable = MemberTableEnum.getName(appPlatform);
		if(StringUtils.isBlank(memberTable)){
			return null;
		}
		return memberDao.queryMemberById(memberTable, id);
	}
	
	@Override
	public boolean updateMember(MemberDTO member) {
		if(member == null){
			return false;
		}
		return memberDao.updateMember(member) > 0;
	}
	
	@Override
	public boolean removeMemberById(MemberDTO member) {
		if(member == null || StringUtils.isBlank(member.getMemberTable()) ||  StringUtils.isBlank(member.getId())){
			return false;
		}
		return memberDao.deleteMemberById(member.getMemberTable(), member.getId()) > 0;
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
