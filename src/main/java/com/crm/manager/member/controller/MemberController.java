package com.crm.manager.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.manager.common.JsonResult;
import com.crm.manager.common.base.controller.BaseController;
import com.crm.manager.common.enums.AppTableEnum;
import com.crm.manager.common.enums.IdentityTypeEnum;
import com.crm.manager.common.enums.SexEnum;
import com.crm.manager.common.enums.StatusEnum;
import com.crm.manager.common.exception.BusinessException;
import com.crm.manager.member.dto.MemberDTO;
import com.crm.manager.member.service.IMemberService;
import com.crm.manager.staff.service.IStaffService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/member")
public class MemberController extends BaseController {
	
	@Autowired
	private IMemberService memberService;
	
	@Autowired
	private IStaffService staffService;
	
	@RequestMapping(value = { "/", "/index" })
	public String index(ModelMap map) {
		map.put("appPlatformList", AppTableEnum.values());
		map.put("identityTypeList", IdentityTypeEnum.values());
		map.put("sexList", SexEnum.values());
		map.put("statusList", StatusEnum.values());
		return "admin/member/index";
	}
	
	
	@RequestMapping(value = { "/list" })
	@ResponseBody
	public PageInfo<MemberDTO> list(MemberDTO memberDTO) {
		PageInfo<MemberDTO> page = memberService.queryMember(memberDTO);
		return page;
	}
	
	@RequestMapping(value = "/addview", method = RequestMethod.GET)
	public String addView(ModelMap map) {
		map.put("appPlatformList", AppTableEnum.values());
		map.put("identityTypeList", IdentityTypeEnum.values());
		map.put("sexList", SexEnum.values());
		map.put("statusList", StatusEnum.values());
		return "admin/member/memberInfo";
	}
	
	@RequestMapping(value= {"/add"} ,method = RequestMethod.POST)
	@ResponseBody
	public JsonResult add(MemberDTO memberDTO){
		try {
			//新增会员
			memberService.addMember(memberDTO);
			return JsonResult.success();
		} catch (BusinessException e) {
			return JsonResult.failure(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return JsonResult.failure("新增异常");
		}
	}

	/*@RequestMapping(value = "/editview", method = RequestMethod.POST)
	public String editView(MemberDTO memberDTO, ModelMap map) {
		MemberDTO member = memberService.queryMemberById(memberDTO.getAppPlatform(), memberDTO.getId);
		map.put("member", member);
		map.put("isEdit", true);
		return "admin/member/memberInfo";
	}*/
	
	@RequestMapping(value = "/editview/{appPlatform}/{id}", method = RequestMethod.GET)
	public String editView(@PathVariable String appPlatform, @PathVariable String id, ModelMap map) {
		MemberDTO member = memberService.queryMemberById(appPlatform, id);
		map.put("appPlatformList", AppTableEnum.values());
		map.put("identityTypeList", IdentityTypeEnum.values());
		map.put("sexList", SexEnum.values());
		map.put("statusList", StatusEnum.values());
		map.put("member", member);
		map.put("isEdit", true);
		map.put("staffList", staffService.queryAllStaff(AppTableEnum.getStaffTableByType(appPlatform)));
		return "admin/member/memberInfo";
	}
	
	@RequestMapping(value= {"/edit"} ,method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(MemberDTO memberDTO){
		try {
			//更新会员
			memberService.updateMember(memberDTO);
			return JsonResult.success();
		} catch (BusinessException e) {
			log.error(e.getMessage(),e);
			return JsonResult.failure(e.getCode(), e.getMessage());
		} catch (Exception e) {
			log.error("更新异常",e);
			return JsonResult.failure("更新异常");
		}
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(MemberDTO memberDTO) {
		try {
			memberService.removeMemberById(memberDTO);
			return JsonResult.success();
		} catch (BusinessException e) {
			log.error(e.getMessage(),e);
			return JsonResult.failure(e.getCode(), e.getMessage());
		} catch (Exception e) {
			log.error("删除异常",e);
			return JsonResult.failure("删除异常");
		}
	}

}
