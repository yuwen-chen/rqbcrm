package com.crm.manager.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.crm.manager.member.domain.Member;

@Mapper
public interface IMemberDao{
	
	/**
     * 批量插入
     *
     * @param member
     * @return
     */
    @Insert("insert into t_a_member (id, name, phone, mail,create_time,update_time) values " +
                "(#{id}, #{name}, #{phone}, #{mail}, now(), now())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertMember(Member member);
    
    /**
     * 查询会员信息
     */
    @Select(" SELECT * FROM t_a_member")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "mail", column = "mail")
    })
    public List<Member> queryAllMember();
    
    /**
     * 查询会员信息
     */
    @Select("<script>" +
	    "SELECT * FROM t_a_member" +
	    "<where> " +
	    "	<if test='id != null'>" + 
        "		and id = #{id} "+
        "	</if>" +
        "	<if test='name != null'>" + 
        "		and name = #{name} "+
        "	</if>" +
        "	<if test='phone != null'>" + 
        "		and phone = #{phone} "+
        "	</if>" +
        "	<if test='mail != null'>" + 
        "		and mail = #{mail} "+
        "	</if>" +
        "</where>" +
	    "</script>")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "mail", column = "mail")
    })
    public List<Member> queryMember(Member member);

}
