package com.crm.manager.staff.service;

import java.util.List;

import com.crm.manager.common.exception.BusinessException;
import com.crm.manager.staff.dto.StaffDTO;
import com.github.pagehelper.PageInfo;

public interface IStaffService {
	
	/**
	 * 修改工作人员信息
	 * @param staffDTO
	 */
	public boolean editStaff(StaffDTO staffDTO)throws BusinessException;
	
	/**
	 * 分页查询工作人员信息
	 * @param memberDTO
	 * @return
	 */
	public PageInfo<StaffDTO> queryStaff(StaffDTO staffDTO)throws BusinessException;
	
	/**
	 * 查询所有工作人员
	 * @param staffTable
	 * @return
	 */
	public List<StaffDTO> queryAllStaff(String staffTable)throws BusinessException;
	
	/**
	 * 通过员工编号查询工作人员信息
	 * @param staffNo
	 * @return
	 */
	public StaffDTO queryStaffByStaffNo(StaffDTO staffDTO)throws BusinessException;
	
	
	/**
	 * 删除工作人员信息
	 * @param staffNo
	 */
	public boolean removeStaff(StaffDTO staffDTO)throws BusinessException;
	
	/**
	 * 查询工作人员会员人数最少的一个
	 * @param staffNo
	 */
	public StaffDTO queryMinMemberNumStaff(String staffTable)throws BusinessException;
}