package com.crm.manager.member.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.manager.common.exception.BusinessException;
import com.crm.manager.common.utils.MemberUtils;
import com.crm.manager.member.dao.IMemberCodeDao;
import com.crm.manager.member.dto.MemberCodeDTO;
import com.crm.manager.member.service.IMemberCodeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class MemberCodeServiceImpl implements IMemberCodeService{
	
	@Autowired
	IMemberCodeDao 	memberCodeDao;

	@Override
	public boolean addMemberCode(MemberCodeDTO memberCode)throws BusinessException {
		if(memberCode == null){
			throw new BusinessException(BusinessException.CODE_FAILURED,"参数错误");
			//return false;
		}
		return memberCodeDao.insertMemberCode(memberCode) > 0;
	}
	
	@Override
	public boolean updateMemberCode(MemberCodeDTO memberCode)throws BusinessException {
		//组装会员编码
		String code = MemberUtils.assemblyMemberCode(memberCode);
		memberCode.setMemberCode(code);
		memberCode.setMemberCodeTable(memberCode.getAppPlatform());
		return memberCodeDao.updateMemberCodeByMemberId(memberCode) > 0;
	}
	
	@Override
	public MemberCodeDTO queryMemberCodeByMemberId(MemberCodeDTO memberCode)throws BusinessException {
		if(memberCode == null || StringUtils.isBlank(memberCode.getMemberCodeTable()) ||  StringUtils.isBlank(memberCode.getMemberId())){
			throw new BusinessException(BusinessException.CODE_FAILURED,"参数错误");
			//return null;
		}
		return memberCodeDao.queryMemberCodeByMemberId(memberCode.getMemberCodeTable(), memberCode.getMemberId());
	}
	
	@Override
	public Integer queryLastCodeNo(String memberCodeTable)throws BusinessException  {
		if(StringUtils.isBlank(memberCodeTable)){
			throw new BusinessException(BusinessException.CODE_FAILURED,"参数错误");
			//return null;
		}
		return memberCodeDao.queryLastCodeNo(memberCodeTable);
	}
	
	@Override
	public boolean removeMemberCodeByMemberId(MemberCodeDTO memberCode)throws BusinessException {
		if(memberCode == null || StringUtils.isBlank(memberCode.getMemberCodeTable()) ||  StringUtils.isBlank(memberCode.getMemberId())){
			throw new BusinessException(BusinessException.CODE_FAILURED,"参数错误");
			//return false;
		}
		return memberCodeDao.deleteMemberCodeByMemberId(memberCode.getMemberCodeTable(), memberCode.getMemberId()) > 0;
	}

	@Override
	public PageInfo<MemberCodeDTO> queryMemberCode(MemberCodeDTO memberCode)throws BusinessException {
		if(memberCode == null){
			throw new BusinessException(BusinessException.CODE_FAILURED,"参数错误");
			//return null;
		}
		PageHelper.startPage(memberCode.getPageNumber(), memberCode.getPageSize());  
		return new PageInfo<MemberCodeDTO>(memberCodeDao.queryMemberCode(memberCode));
	}

}
