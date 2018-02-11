package com.crm.manager.member.controller.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.crm.manager.common.JsonResult;
import com.crm.manager.common.base.controller.BaseController;
import com.crm.manager.common.exception.BusinessException;
import com.crm.manager.member.dto.MemberDTO;
import com.crm.manager.member.service.IMemberService;

@CrossOrigin
@RestController
@RequestMapping("/app/member")
public class MemberAppRest extends BaseController{
	
	@Autowired
	private IMemberService memberService;
	
	@RequestMapping(value = "/addmember", method = RequestMethod.POST)
	public JsonResult addMember(String data_body) {
		try {
			if(StringUtils.isNotBlank(data_body)){
				MemberDTO member = JSON.parseObject(data_body, MemberDTO.class);
				memberService.addMember(member);
				return JsonResult.success();
			}
			return JsonResult.failure(BusinessException.CODE_FAILURED, "参数异常");
		} catch (BusinessException e) {
			log.error(e.getMessage(),e);
			return JsonResult.failure(e.getCode(), e.getMessage());
		} catch (Exception e) {
			log.error("新增会员异常",e);
			return JsonResult.failure("新增会员异常");
		}
	}
}
