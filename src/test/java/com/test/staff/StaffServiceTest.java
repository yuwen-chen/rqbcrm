package com.test.staff;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crm.manager.Application;
import com.crm.manager.staff.dto.StaffDTO;
import com.crm.manager.staff.service.IStaffService;
import com.github.pagehelper.PageInfo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class StaffServiceTest {
	
	@Autowired
	private IStaffService staffService;
	
	@Test
	public void queryTest(){
		StaffDTO staffDTO = new StaffDTO();
		staffDTO.setStaffTable("A");
		staffDTO.setPageNumber(1);
		staffDTO.setPageSize(10);
		PageInfo<StaffDTO> page = staffService.queryStaff(staffDTO);
		System.out.println(page.getTotal());
	}
	

}
