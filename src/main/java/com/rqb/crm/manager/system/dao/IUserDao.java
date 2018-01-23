package com.rqb.crm.manager.system.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.rqb.crm.manager.common.base.dao.IBaseDao;
import com.rqb.crm.manager.system.dto.UserDTO;

@Repository
public interface IUserDao extends IBaseDao<UserDTO, Integer> {

	UserDTO findByUserName(String username);

	Page<UserDTO> findAllByNickNameContaining(String searchText, Pageable pageable);

}
