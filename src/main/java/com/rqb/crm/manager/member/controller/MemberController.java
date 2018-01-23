package com.rqb.crm.manager.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.rqb.crm.manager.config.mybatis.PaginationInfo;
import com.rqb.crm.manager.member.domain.Member;
import com.rqb.crm.manager.member.service.IMemberService;

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
	public PageInfo<Member> list(
			@RequestParam(required=true,defaultValue="1")Integer pageNumber,
			@RequestParam(required=true,defaultValue="1")Integer pageSize) {
		PageInfo<Member> page = memberService.queryMember(pageNumber, pageSize);
		return page;
	}

}
