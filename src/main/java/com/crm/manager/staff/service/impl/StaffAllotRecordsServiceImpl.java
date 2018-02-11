package com.crm.manager.staff.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.manager.common.exception.BusinessException;
import com.crm.manager.staff.dao.IStaffAllotRecordsDao;
import com.crm.manager.staff.dto.StaffAllotRecordsDTO;
import com.crm.manager.staff.service.IStaffAllotRecordsService;

@Service
public class StaffAllotRecordsServiceImpl implements IStaffAllotRecordsService{
	
	@Autowired
	private IStaffAllotRecordsDao staffAllotRecordsDao;

	@Override
	public boolean addStaffAllotRecords(StaffAllotRecordsDTO staffAllotRecordsDTO)throws BusinessException  {
		if(staffAllotRecordsDTO == null){
			throw new BusinessException(BusinessException.CODE_FAILURED,"参数错误");
		}
		return staffAllotRecordsDao.inserStaffAllotRecords(staffAllotRecordsDTO) > 0;
	}

	@Override
	public List<StaffAllotRecordsDTO> queryStaffAllotRecordsByStaffNo(String allotRecordsTable, String staffNo) throws BusinessException {
		return staffAllotRecordsDao.queryStaffAllotRecordsByStaffNo(allotRecordsTable, staffNo);
	}

	@Override
	public int queryMemberNum(String allotRecordsTable, String staffNo)throws BusinessException  {
		return staffAllotRecordsDao.queryMemberNum(allotRecordsTable, staffNo);
	}

	/**
	 * 删除分配记录
	 * @param staffAllotRecords
	 * @return
	 */
	public boolean removeStaffAllotRecords(StaffAllotRecordsDTO staffAllotRecords)throws BusinessException {
		if(staffAllotRecords == null || StringUtils.isBlank(staffAllotRecords.getAllotRecordsTable())
				|| StringUtils.isBlank(staffAllotRecords.getMemberId()) || StringUtils.isBlank(staffAllotRecords.getStaffNo())){
			throw new BusinessException(BusinessException.CODE_FAILURED,"参数错误");
			//return false;
		}
		return staffAllotRecordsDao.deleteStaffAllotRecords(staffAllotRecords) > 0;
	}
}
