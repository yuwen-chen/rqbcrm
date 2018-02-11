package com.crm.manager.investment.service;

import com.crm.manager.common.exception.BusinessException;
import com.crm.manager.investment.dto.InvestmentRecordsDTO;
import com.github.pagehelper.PageInfo;

public interface IInvestmentRecordsService {
	
	/**
	 * 新增投资记录
	 * @param investmentRecordsDTO
	 */
	public void addInvestmentRecords(InvestmentRecordsDTO investmentRecordsDTO) throws BusinessException;
	
	/**
	 * 分页查询投资记录
	 * @param investmentRecordsDTO
	 * @return
	 */
	public PageInfo<InvestmentRecordsDTO> queryInvestmentRecords(InvestmentRecordsDTO investmentRecordsDTO)throws BusinessException;
}
