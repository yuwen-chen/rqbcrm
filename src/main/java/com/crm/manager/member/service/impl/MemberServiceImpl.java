package com.crm.manager.member.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.manager.common.enums.AppTableEnum;
import com.crm.manager.common.exception.BusinessException;
import com.crm.manager.common.utils.MemberUtils;
import com.crm.manager.member.dao.IMemberDao;
import com.crm.manager.member.dto.MemberCodeDTO;
import com.crm.manager.member.dto.MemberDTO;
import com.crm.manager.member.service.IMemberCodeService;
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
	private IMemberCodeService memberCodeService;
	
	@Autowired
	private IStaffAllotRecordsService staffAllotRecordsService;
	
	@Override
	@Transactional
	public void addMember(MemberDTO member) throws BusinessException {
		//校验参数
		this.validationParam(member);
		if(StringUtils.isBlank(member.getRegisterDate())){
			member.setRegisterDate(new SimpleDateFormat("yyMM").format(new Date()));
		}
		member.setMemberTable(member.getAppPlatform());
		MemberDTO exist = memberDao.queryMemberById(member.getMemberTable(), member.getId());
		if(exist != null){
			throw new BusinessException(500,"用户ID重复");
		}
		//查询会员人数最少的工作人员编号进行分配
		StaffDTO staff = staffService.queryMinMemberNumStaff(AppTableEnum.getStaffTableByType(member.getAppPlatform()));
		if(staff == null || StringUtils.isBlank(staff.getStaffNo())){
			throw new BusinessException(500,"没有对应的工作人员信息");
		}
		member.setStaffNo(staff.getStaffNo());
		StaffAllotRecordsDTO staffAllotRecordsDTO = new StaffAllotRecordsDTO();
		//新增分配记录
		staffAllotRecordsDTO.setStaffNo(staff.getStaffNo());
		staffAllotRecordsDTO.setMemberId(member.getId());
		staffAllotRecordsDTO.setAllotRecordsTable(member.getAppPlatform());

		boolean result = staffAllotRecordsService.addStaffAllotRecords(staffAllotRecordsDTO);
		if(result){
			result = memberDao.insertMember(member) > 0;
		}
		//更新工作人员表
		staff.setMemberNum(staff.getMemberNum()+1);
		staff.setStaffTable(member.getAppPlatform());
		if(result){
			result = staffService.editStaff(staff);
		}
		if(!result){
			throw new BusinessException(500,"更新工作人员信息错误");
		}
		//新增会员编码表
		MemberCodeDTO memberCode = new MemberCodeDTO();
		memberCode.setMemberCodeTable(member.getAppPlatform());
		memberCode.setAppPlatform(member.getAppPlatform());
		memberCode.setStaffNo(staff.getStaffNo());
		//组装会员编码信息
		this.assemblyMemberCodeInfo(memberCode, member);
		
		//组装编码
		String code = MemberUtils.assemblyMemberCode(memberCode);
		memberCode.setMemberCode(code);
		//新增会员编码表
		result = memberCodeService.addMemberCode(memberCode);
	}
	
	@Override
	public MemberDTO queryMemberById(String appPlatform, String id) throws BusinessException {
		if(StringUtils.isBlank(appPlatform) ||  StringUtils.isBlank(id)){
			throw new BusinessException(500,"参数错误");
			//return null;
		}
		String memberTable = AppTableEnum.getMemberTableByType(appPlatform);
		return memberDao.queryMemberById(memberTable, id);
	}
	
	@Override
	@Transactional
	public void updateMember(MemberDTO member)throws BusinessException  {
		if(member == null){
			throw new BusinessException(500,"参数错误");
		}
		if(StringUtils.isBlank(member.getId())){
			throw new BusinessException(500,"用户ID不能为空");
		}
		if(StringUtils.isBlank(member.getAppPlatform())){
			throw new BusinessException(500,"app平台不能为空");
		}
		MemberDTO memberDto =queryMemberById(member.getAppPlatform(),member.getId());
		if(memberDto == null){
			throw new BusinessException(500,"用户ID不存在");
		}
		//修改工作人员编号流程
		if(!memberDto.getStaffNo().equals(member.getStaffNo())){
			MemberDTO memberResult = this.queryMemberById(member.getAppPlatform(), member.getId());
			StaffAllotRecordsDTO staffAllotRecordsDTO = new StaffAllotRecordsDTO();
			staffAllotRecordsDTO.setAllotRecordsTable(memberResult.getAppPlatform());
			staffAllotRecordsDTO.setStaffNo(memberResult.getStaffNo());
			staffAllotRecordsDTO.setMemberId(memberResult.getId());
			//删除旧的分配记录
			boolean removeResult = staffAllotRecordsService.removeStaffAllotRecords(staffAllotRecordsDTO);
			if(!removeResult){
				throw new BusinessException(500,"删除旧的分配记录失败");
				//return false;
			}
			staffAllotRecordsDTO.setStaffNo(member.getStaffNo());
			//新增新的分配记录
			boolean addResult = staffAllotRecordsService.addStaffAllotRecords(staffAllotRecordsDTO);
			if(!addResult){
				throw new BusinessException(500,"新增新的分配记录失败");
				//return false;
			}
			StaffDTO staffParam = new StaffDTO();
			//更新旧的工作人员表
			staffParam .setStaffTable(memberResult.getAppPlatform());
			staffParam.setStaffNo(memberResult.getStaffNo());
			StaffDTO staff = staffService.queryStaffByStaffNo(staffParam);
			if(staff == null){
				throw new BusinessException(500,"没有对应旧的工作人员信息");
				//return false;
			}
			staff.setStaffTable(staff.getAppPlatform());
			if(staff.getMemberNum() > 0){
				staff.setMemberNum(staff.getMemberNum()-1);
			}
			boolean oldResult = staffService.editStaff(staff);
			if(!oldResult){
				throw new BusinessException(500,"更新旧的工作人员信息错误");
				//return false;
			}
			//更新新的工作人员表
			staffParam.setStaffNo(member.getStaffNo());
			StaffDTO staffResult = staffService.queryStaffByStaffNo(staffParam);
			if(staffResult == null){
				throw new BusinessException(500,"没有对应新的工作人员信息");
				//return false;
			}
			staffResult .setMemberNum(staffResult.getMemberNum() + 1);
			staffResult.setStaffTable(staffResult.getAppPlatform());
			boolean newResult = staffService.editStaff(staffResult);
			if(!newResult){
				throw new BusinessException(500,"更新新的工作人员信息错误");
				//return false;
			}
			//查询当前会员编码
			MemberCodeDTO memberCodeParam = new MemberCodeDTO();
			memberCodeParam.setMemberCodeTable(member.getAppPlatform());
			memberCodeParam.setMemberId(member.getId());
			MemberCodeDTO memberCode = memberCodeService.queryMemberCodeByMemberId(memberCodeParam);
			//修改工作人员编号
			memberCode.setStaffNo(member.getStaffNo());
			//更新会员编码表
			boolean result = memberCodeService.updateMemberCode(memberCode);
			if(!result){
				throw new BusinessException(500,"更新会员编码失败");
				//return false;
			}
			memberDao.updateMember(member);
		}
		memberDao.updateMember(member);
	}
	
	@Override
	public void removeMemberById(MemberDTO member) throws BusinessException {
		if(member == null){
			throw new BusinessException(500,"参数错误");
		}
		if(StringUtils.isBlank(member.getMemberTable())){
			throw new BusinessException(500,"app平台不能为空");
		}
		if(StringUtils.isBlank(member.getId())){
			throw new BusinessException(500,"用户ID不能为空");
		}
		MemberDTO exist =memberDao.queryMemberById(member.getMemberTable(), member.getId());
		if(exist == null){
			throw new BusinessException(500,"用户ID不存在");
		}
		memberDao.deleteMemberById(member.getMemberTable(), member.getId());
	}

	@Override
	public PageInfo<MemberDTO> queryMember(MemberDTO memberDTO) throws BusinessException {
		if(memberDTO == null){
			throw new BusinessException(500,"参数错误");
			//return null;
		}
		PageHelper.startPage(memberDTO.getPageNumber(), memberDTO.getPageSize());  
		return new PageInfo<MemberDTO>(memberDao.queryMember(memberDTO));
	}
	
	/**
	 * 检验参数
	 * @param member
	 */
	private void validationParam(MemberDTO member){
		if(member == null){
			throw new BusinessException(BusinessException.CODE_FAILURED,"参数错误");
		}
		if(StringUtils.isBlank(member.getId())){
			throw new BusinessException(BusinessException.CODE_FAILURED,"用户ID不能为空");
		}
		if(StringUtils.isBlank(member.getAppPlatform())){
			throw new BusinessException(BusinessException.CODE_FAILURED,"app平台不能为空");
		}
		if(StringUtils.isBlank(member.getIdentityType())){
			throw new BusinessException(BusinessException.CODE_FAILURED,"证件类型不能为空");
		}
		if(StringUtils.isBlank(member.getIdentityNo())){
			throw new BusinessException(BusinessException.CODE_FAILURED,"证件号不能为空");
		}
		if(StringUtils.isBlank(member.getRealName())){
			throw new BusinessException(BusinessException.CODE_FAILURED,"真实姓名不能为空");
		}
		if(member.getFinancialLevel() == null){
			throw new BusinessException(BusinessException.CODE_FAILURED,"理财等级不能为空");
		}
		
		if(member.getSex()== null){
			throw new BusinessException(BusinessException.CODE_FAILURED,"性别不能为空");
		}
	}
	
	/**
	 * 解析身份号
	 * @param memberCode
	 * @param member
	 */
	private void assemblyMemberCodeInfo(MemberCodeDTO memberCode, MemberDTO member){
		if(StringUtils.isBlank(member.getIdentityType()) || StringUtils.isBlank(member.getIdentityNo())){
			return;
		}
		//身份证号
		String indentityNo = member.getIdentityNo();
		String indentityType = member.getIdentityType();
		String birthdate = "";
		String province = "";
		if("01".equals(indentityType)){
			birthdate = indentityNo.substring(8, 14);
			if(indentityNo.length() != 18){
				birthdate = indentityNo.substring(6, 12);
			}
			province = indentityNo.substring(0, 2);
		}
		
		//查询最后一位客户编号
		Integer num = memberCodeService.queryLastCodeNo(memberCode.getMemberCodeTable());
		if(num != null){
			memberCode.setCodeNo(String.format("%08d", num + 1));
		} else {
			memberCode.setCodeNo(String.format("%08d", 1));
		}
		memberCode.setMemberId(member.getId());
		memberCode.setProvince(province);
		memberCode.setFinancialLevel(member.getFinancialLevel());
		memberCode.setInvestmentAmount("0000");
		memberCode.setIsInvestment(0);
		memberCode.setRegisterDate(member.getRegisterDate());
		memberCode.setBirthdate(birthdate);
		memberCode.setSex(member.getSex());
	}

}
