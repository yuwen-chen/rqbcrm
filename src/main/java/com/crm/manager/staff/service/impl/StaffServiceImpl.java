package com.crm.manager.staff.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.manager.common.exception.BusinessException;
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
	public boolean editStaff(StaffDTO staffDTO)throws BusinessException {
		if(staffDTO == null){
			throw new BusinessException(BusinessException.CODE_FAILURED,"参数错误");
			//return false;
		}
		if (staffDTO.getId() == null){
			return staffDao.insertStaff(staffDTO) > 0;
		}
		return staffDao.updateStaff(staffDTO) > 0;
		
	}

	@Override
	public PageInfo<StaffDTO> queryStaff(StaffDTO staffDTO)throws BusinessException {
		if(staffDTO == null){
			throw new BusinessException(BusinessException.CODE_FAILURED,"参数错误");
			//return null;
		}
		PageHelper.startPage(staffDTO.getPageNumber(), staffDTO.getPageSize()); 
		return new PageInfo<StaffDTO>(staffDao.queryStaff(staffDTO));
	}
	
	@Override
	public List<StaffDTO> queryAllStaff(String staffTable)throws BusinessException{
		if(StringUtils.isBlank(staffTable)){
			throw new BusinessException(BusinessException.CODE_FAILURED,"参数错误");
			//return null;
		}
		return staffDao.queryAllStaff(staffTable);
	}
	
	@Override
	public StaffDTO queryStaffByStaffNo(StaffDTO staffDTO)throws BusinessException {
		if(staffDTO == null || StringUtils.isBlank(staffDTO.getStaffTable()) 
				|| StringUtils.isBlank(staffDTO.getStaffNo())){
			throw new BusinessException(BusinessException.CODE_FAILURED,"参数错误");
			//return null;
		}
		return staffDao.queryStaffByStaffNo(staffDTO.getStaffTable(), staffDTO.getStaffNo());
	}

	@Override
	public boolean removeStaff(StaffDTO staffDTO)throws BusinessException {
		if(staffDTO == null || StringUtils.isBlank(staffDTO.getStaffTable()) 
				|| StringUtils.isBlank(staffDTO.getStaffNo())){
			throw new BusinessException(BusinessException.CODE_FAILURED,"参数错误");
			//return false;
		}
		return staffDao.deleteStaffByStaffNo(staffDTO.getStaffTable(), staffDTO.getStaffNo()) > 0;
	}

	@Override
	public StaffDTO queryMinMemberNumStaff(String staffTable)throws BusinessException {
		return staffDao.queryMinMemberNumStaff(staffTable);
	}

}
