package com.crm.manager.staff.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.manager.staff.dao.IStaffDao;
import com.crm.manager.staff.dto.StaffDTO;
import com.crm.manager.staff.service.IStaffService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class StaffServiceImpl implements IStaffService{
	
	@Autowired
	private IStaffDao staffDao;

	@Override
	public boolean editStaff(StaffDTO staffDTO) {
		if(staffDTO == null){
			return false;
		}
		if (staffDTO.getId() == null){
			return staffDao.insertStaff(staffDTO) > 0;
		}
		return staffDao.updateStaff(staffDTO) > 0;
		
	}

	@Override
	public PageInfo<StaffDTO> queryStaff(StaffDTO staffDTO) {
		if(staffDTO == null){
			return null;
		}
		PageHelper.startPage(staffDTO.getPageNumber(), staffDTO.getPageSize()); 
		return new PageInfo<StaffDTO>(staffDao.queryStaff(staffDTO));
	}
	
	@Override
	public StaffDTO queryStaffByStaffNo(String staffNo) {
		return staffDao.queryStaffByStaffNo(staffNo);
	}

	@Override
	public boolean removeStaff(String staffNo) {
		if(StringUtils.isBlank(staffNo)){
			return false;
		}
		return staffDao.deleteStaffByStaffNo(staffNo) > 0;
	}

	@Override
	public StaffDTO queryMinMemberNumStaff() {
		return staffDao.queryMinMemberNumStaff();
	}

}
