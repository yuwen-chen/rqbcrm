package com.crm.manager.common.utils;

import org.apache.commons.lang3.StringUtils;

import com.crm.manager.common.exception.BusinessException;
import com.crm.manager.member.dto.MemberCodeDTO;

public class MemberUtils {
	
	public static String assemblyMemberCode(MemberCodeDTO memberCode) throws BusinessException{
		if(memberCode == null){
			throw new BusinessException(500,"参数错误");
			//return null;
		}
		if(StringUtils.isBlank(memberCode.getAppPlatform())){
			throw new BusinessException(500,"app平台不能为空");
			//return null;
		}
		if(StringUtils.isBlank(memberCode.getProvince())){
			throw new BusinessException(500,"省份不能为空");
			//return null;
		}
		if(memberCode.getIsInvestment() == null){
			throw new BusinessException(500,"是否投资理财不能为空");
			//return null;
		}
		if(memberCode.getFinancialLevel() == null){
			throw new BusinessException(500,"投资等级不能为空");
			//return null;
		}
		if(StringUtils.isBlank(memberCode.getInvestmentAmount())){
			throw new BusinessException(500,"投资金额不能为空");
			//return null;
		}
		if(StringUtils.isBlank(memberCode.getRegisterDate())){
			throw new BusinessException(500,"注册日期不能为空");
			//return null;
		}
		if(StringUtils.isBlank(memberCode.getBirthdate())){
			throw new BusinessException(500,"出生年月不能为空");
			//return null;
		}
		if(memberCode.getSex() == null ){
			throw new BusinessException(500,"性别不能为空");
			//return null;
		}
		if(StringUtils.isBlank(memberCode.getStaffNo())){
			throw new BusinessException(500,"工作人员编号不能为空");
			//return null;
		}
		if(StringUtils.isBlank(memberCode.getCodeNo())){
			throw new BusinessException(500,"客户编号不能为空");
			//return null;
		}
		StringBuffer code = new StringBuffer(memberCode.getProvince()).
				append(memberCode.getAppPlatform()).append(memberCode.getIsInvestment()).append(memberCode.getFinancialLevel())
				.append(memberCode.getInvestmentAmount()).append(memberCode.getRegisterDate()).append(memberCode.getBirthdate())
				.append(memberCode.getSex()).append(memberCode.getStaffNo()).append(memberCode.getCodeNo());
		return code.toString();
	}
	
	
	public static void main(String[] args) {
		
	}

}
