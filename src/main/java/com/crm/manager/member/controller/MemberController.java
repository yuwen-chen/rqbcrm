package com.crm.manager.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.manager.member.domain.Member;
import com.crm.manager.member.dto.MemberDTO;
import com.crm.manager.member.service.IMemberService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/member")
public class MemberController {
	
	@Autowired
	IMemberService memberService;
	
	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "admin/member/index";
	}
	
	
	@RequestMapping(value = { "/list" })
	@ResponseBody
	public PageInfo<Member> list(MemberDTO memberInfo) {
		PageInfo<Member> page = memberService.queryMember(memberInfo);
		return page;
	}

}
