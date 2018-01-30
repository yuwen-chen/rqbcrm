/*package com.crm.manager.staff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.manager.member.dto.MemberDTO;
import com.crm.manager.member.enums.MemberTableEnum;
import com.crm.manager.member.service.IMemberService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/member")
public class StaffController {
	
	@Autowired
	IMemberService memberService;
	
	@RequestMapping(value = { "/", "/index" })
	public String index(ModelMap map) {
		map.put("appPlatformList", MemberTableEnum.values());
		return "admin/member/index";
	}
	
	
	@RequestMapping(value = { "/list" })
	@ResponseBody
	public PageInfo<MemberDTO> list(MemberDTO memberDTO) {
		PageInfo<MemberDTO> page = memberService.queryMember(memberDTO);
		return page;
	}

}
*/