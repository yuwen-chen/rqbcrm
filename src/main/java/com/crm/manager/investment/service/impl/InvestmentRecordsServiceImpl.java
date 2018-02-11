package com.crm.manager.investment.service.impl;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.manager.common.exception.BusinessException;
import com.crm.manager.investment.dao.IInvestmentRecordsDao;
import com.crm.manager.investment.dto.InvestmentRecordsDTO;
import com.crm.manager.investment.service.IInvestmentRecordsService;
import com.crm.manager.member.dto.MemberCodeDTO;
import com.crm.manager.member.service.IMemberCodeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class InvestmentRecordsServiceImpl implements IInvestmentRecordsService{
	
	@Autowired
	private IInvestmentRecordsDao investmentRecordsDao;
	
	@Autowired
	private IMemberCodeService memberCodeService;

	@Override
	@Transactional
	public void addInvestmentRecords(InvestmentRecordsDTO investmentRecordsDTO) throws BusinessException{
		
		if(investmentRecordsDTO == null){
			throw new BusinessException(BusinessException.CODE_FAILURED,"参数错误");
		}
		if(StringUtils.isBlank(investmentRecordsDTO.getAppPlatform())){
			throw new BusinessException(BusinessException.CODE_FAILURED,"app平台不能为空");
		}
		if(StringUtils.isBlank(investmentRecordsDTO.getMemberId())){
			throw new BusinessException(BusinessException.CODE_FAILURED,"用户ID不能为空");
		}
		if(StringUtils.isBlank(investmentRecordsDTO.getOrderNo())){
			throw new BusinessException(BusinessException.CODE_FAILURED,"订单号不能为空");
		}
		investmentRecordsDTO.setInvestmentRecordsTable(investmentRecordsDTO.getAppPlatform());
		InvestmentRecordsDTO exist = investmentRecordsDao.queryInvestmentRecordsByOrderNo(investmentRecordsDTO.getInvestmentRecordsTable(), investmentRecordsDTO.getOrderNo());
		if(exist != null){
			throw new BusinessException(BusinessException.CODE_FAILURED,"订单号重复");
		}
		
		//更新编码表是投资理财
		investmentRecordsDao.insertInvestmentRecords(investmentRecordsDTO);
		MemberCodeDTO memberCodeDTO = new MemberCodeDTO();
		memberCodeDTO.setMemberId(investmentRecordsDTO.getMemberId());
		memberCodeDTO.setMemberCodeTable(investmentRecordsDTO.getAppPlatform());
		MemberCodeDTO memberCode = memberCodeService.queryMemberCodeByMemberId(memberCodeDTO);
		if(memberCode == null){
			throw new BusinessException(BusinessException.CODE_FAILURED,"没有对于的用户ID");
		}
		int investmentAmount = 0;
		String amount = memberCode.getInvestmentAmount();
		investmentAmount = Integer.parseInt(amount != null ? amount : "0");
		memberCode.setIsInvestment(1);
		BigDecimal totalAmount = investmentRecordsDTO.getTotalPrice();
		if (totalAmount != null) {
			//投资金额(1-9999元起算1w=0001,逢1w进1)
			investmentAmount = investmentAmount + getInvestmentAmount(totalAmount);
			//格式化"0000"
			memberCode.setInvestmentAmount(String.format("%04d", investmentAmount));
		}
		memberCodeService.updateMemberCode(memberCode);
	}

	@Override
	public PageInfo<InvestmentRecordsDTO> queryInvestmentRecords(InvestmentRecordsDTO investmentRecordsDTO)throws BusinessException {
		if(investmentRecordsDTO == null){
			throw new BusinessException(BusinessException.CODE_FAILURED,"参数错误");
		}
		if(investmentRecordsDTO.getPageNumber() ==null){
			throw new BusinessException(BusinessException.CODE_FAILURED,"当前页不能为空");
		}
		if(investmentRecordsDTO.getPageSize() == null){
			throw new BusinessException(BusinessException.CODE_FAILURED,"每页条数不能为空");
		}
		PageHelper.startPage(investmentRecordsDTO.getPageNumber(), investmentRecordsDTO.getPageSize());  
		return new PageInfo<InvestmentRecordsDTO>(investmentRecordsDao.queryInvestmentRecords(investmentRecordsDTO));
	}
	
	
	/**
	 * 根据投资金额计算出多少W
	 * @param totalAmount
	 * 投资金额(1-9999元起算1w=0001,逢1w进1)
	 * @return
	 */
	private int getInvestmentAmount(BigDecimal totalAmount){
		BigDecimal minAmount = BigDecimal.valueOf(1); 
		BigDecimal maxAmount = BigDecimal.valueOf(9999); 
		BigDecimal multipleNum = BigDecimal.valueOf(10000); 
		if(totalAmount.compareTo(minAmount) >= 0 && totalAmount.compareTo(maxAmount) <= 0){
			return 1;
		} else if(totalAmount.compareTo(maxAmount) >0 ){
			int multiple =totalAmount.divide(multipleNum).intValue();
			BigDecimal remainder =totalAmount.subtract(multipleNum.multiply(new BigDecimal(multiple)));
			if (multiple == 0){
				return 1;
			}
			if((remainder.compareTo(minAmount) >= 0 && remainder.compareTo(maxAmount) <= 0)){
				return multiple + 1;
			} else {
				return multiple;
			}
		}
		return 0;
	}

}
