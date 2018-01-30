package com.crm.manager.staff.dao;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.crm.manager.staff.dto.StaffDTO;

@Mapper
public interface IStaffDao{
	
	/**
     * 插入工作人员信息
     *
     * @param staffDTO
     * @return
     */
    @Insert("<script>" +
    		"insert into t_staff ( " + 
    		"id, " +
    		"staff_no, " +
    		"name, " +
    		"phone, " +
    		"sex, " +
		    "	<if test='status != null'>" + 
		    "		status, " +
	        "	</if>" +
    		"email, " +
    		"member_num, " +
    		"create_time, " +
    		"update_time "+
    		") values ( " +
            "#{id}, "+
    		"#{staffNo}, " +
    		"#{name}, " +
            "#{phone}, " +
    		"#{sex}, " +
		    "	<if test='status != null'>" + 
            "		#{status}, " +
	        "	</if>" +
            "#{email}, " +
    		"#{memberNum}, " +
            "now(), now()" +
    		") "+
			"</script>")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertStaff(StaffDTO staffDTO);
    
    
	/**
     * 更新工作人员信息
     *
     * @param staffDTO
     * @return
     */
    @Update("<script>" +
    		"update t_staff " + 
    		"<set> " +
		    "	<if test='name != null'>" + 
		    "		name = #{name}, " +
	        "	</if>" +
		    "	<if test='phone != null'>" + 
		    "		phone = #{phone}, " +
	        "	</if>" +
		    "	<if test='sex != null'>" + 
		    "		sex = #{sex}, " +
	        "	</if>" +
		    "	<if test='status != null'>" + 
		    "		status = #{status}, " +
	        "	</if>" +
		    "	<if test='email != null'>" + 
		    "		email = #{email}, " +
	        "	</if>" +
		    "	<if test='memberNum != null'>" + 
		    "		member_num = #{memberNum}, " +
	        "	</if>" +
    		"	update_time = now() "+
	        "</set>" +
    		"where staff_no = #{staffNo} " +
			"</script>")
    public int updateStaff(StaffDTO staffDTO);
    
    /**
     * 查询工作人员信息
     */
    @Select(" SELECT * FROM t_staff ")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "staffNo", column = "staff_no"),
        @Result(property = "name", column = "name"),
        @Result(property = "phone", column = "phone"),
        @Result(property = "sex", column = "sex"),
        @Result(property = "status", column = "status"),
        @Result(property = "mail", column = "mail"),
        @Result(property = "memberNum", column = "member_num")
    })
    public List<StaffDTO> queryAllStaff();
    
    /**
     * 查询工作人员会员人数最少的一个
     */
    @Select(" SELECT * FROM t_staff order by member_num,staff_no asc limit 1 ")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "staffNo", column = "staff_no"),
        @Result(property = "name", column = "name"),
        @Result(property = "phone", column = "phone"),
        @Result(property = "sex", column = "sex"),
        @Result(property = "status", column = "status"),
        @Result(property = "mail", column = "mail"),
        @Result(property = "memberNum", column = "member_num")
    })
    public StaffDTO queryAllStaffOrderByMemberNum();
    
    /**
     * 查询工作人员信息
     */
    @Select("<script>" +
    		"SELECT * FROM t_staff " +
		    "<where> " +
		    "	<if test='id != null'>" + 
	        "		and id = #{id} "+
	        "	</if>" +
	        "	<if test='staffNo != null'>" + 
	        "		and staff_no = #{staffNo} "+
	        "	</if>" +
	        "	<if test='name != null'>" + 
	        "		and name = #{name} "+
	        "	</if>" +
	        "	<if test='phone != null'>" + 
	        "		and phone = #{phone} "+
	        "	</if>" +
	        "	<if test='sex != null'>" + 
	        "		and sex = #{sex} "+
	        "	</if>" +
	        "	<if test='status != null'>" + 
	        "		and status = #{status} "+
	        "	</if>" +
	        "	<if test='email != null'>" + 
	        "		and email = #{email} "+
	        "	</if>" +
	        "	<if test='memberNum != null'>" + 
	        "		and member_num = #{memberNum} "+
	        "	</if>" +
	        "</where>" +
		    "</script>")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "staffNo", column = "staff_no"),
        @Result(property = "name", column = "name"),
        @Result(property = "phone", column = "phone"),
        @Result(property = "sex", column = "sex"),
        @Result(property = "status", column = "status"),
        @Result(property = "mail", column = "mail"),
        @Result(property = "memberNum", column = "member_num")
    })
    public List<StaffDTO> queryStaff(StaffDTO staffDTO);
    
    /**
     * 通过员工编号查询工作人员信息
     */
    @Select("<script>" +
    		"SELECT * FROM t_staff " +
		    "where staff_no = #{staffNo} " +
		    "</script>")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "staffNo", column = "staff_no"),
        @Result(property = "name", column = "name"),
        @Result(property = "phone", column = "phone"),
        @Result(property = "sex", column = "sex"),
        @Result(property = "status", column = "status"),
        @Result(property = "mail", column = "mail"),
        @Result(property = "memberNum", column = "member_num")
    })
    public StaffDTO queryStaffByStaffNo(@Param(value = "staffNo") String staffNo);
    
    /**
     * 通过员工编号删除工作人员信息
     */
    @Delete("<script>" +
    		"DELETE FROM t_staff " +
		    "where staff_no = #{staffNo} " +
		    "</script>")
    public int deleteStaffByStaffNo(@Param(value = "staffNo") String staffNo);

}