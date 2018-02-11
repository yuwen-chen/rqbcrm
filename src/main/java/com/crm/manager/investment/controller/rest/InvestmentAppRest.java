package com.crm.manager.investment.controller.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.crm.manager.common.JsonResult;
import com.crm.manager.common.base.controller.BaseController;
import com.crm.manager.common.exception.BusinessException;
import com.crm.manager.investment.dto.InvestmentRecordsDTO;
import com.crm.manager.investment.service.IInvestmentRecordsService;

@CrossOrigin
@RestController
@RequestMapping("/app/investment")
public class InvestmentAppRest extends BaseController{
	
	@Autowired
	private IInvestmentRecordsService investmentRecordsService;
	
	@RequestMapping(value = "/applyinvestment")
	public JsonResult applyInvestment(String data_body) {
		try {
			if(StringUtils.isNotBlank(data_body)){
				InvestmentRecordsDTO investmentRecordsDTO = JSON.parseObject(data_body, InvestmentRecordsDTO.class);
				investmentRecordsService.addInvestmentRecords(investmentRecordsDTO);
				return JsonResult.success();
			}
			return JsonResult.failure(BusinessException.CODE_FAILURED, "参数异常");
		} catch (BusinessException e) {
			log.error(e.getMessage(),e);
			return JsonResult.failure(e.getCode(), e.getMessage());
		} catch (Exception e) {
			log.error("投资异常",e);
			return JsonResult.failure("投资异常");
		}
	}
}
