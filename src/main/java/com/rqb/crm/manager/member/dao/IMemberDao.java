package com.rqb.crm.manager.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.rqb.crm.manager.member.domain.Member;

@Mapper
public interface IMemberDao{
	
	/**
     * 批量插入
     *
     * @param member
     * @return
     */
    @Insert("insert into rqb_member (id, name, phone, mail,create_time,update_time) values " +
                "(#{id}, #{name}, #{phone}, #{mail}, now(), now())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertMember(Member member);
    
    /**
     * 查询会员信息
     */
    @Select(" SELECT * FROM rqb_member")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "mail", column = "mail")
    })
    public List<Member> queryMember();

}
