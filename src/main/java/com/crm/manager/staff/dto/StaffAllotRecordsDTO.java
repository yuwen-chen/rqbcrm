package com.crm.manager.staff.dto;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.crm.manager.common.base.dto.BaseDTO;

public class StaffAllotRecordsDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5691634491994036533L;
	
	/**
	 * 记录ID
	 */
	private Long id;
	
	/**
	 * 工作人员ID
	 */
	private String staffNo;
	
	/**
	 * 会员ID
	 */
	private String memberId;
	
	/**
	 * 分配时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date allotTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Date getAllotTime() {
		return allotTime;
	}

	public void setAllotTime(Date allotTime) {
		this.allotTime = allotTime;
	}

	@Override
	public String toString() {
		return "StaffAllotRecordsDTO [id=" + id + ", staffNo=" + staffNo + ", memberId=" + memberId + ", allotTime="
				+ allotTime + "]";
	}
	
}