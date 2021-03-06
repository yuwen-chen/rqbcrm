package com.rqb.crm.manager.system.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.rqb.crm.manager.common.base.dao.IBaseDao;
import com.rqb.crm.manager.system.dto.RoleDTO;

@Repository
public interface IRoleDao extends IBaseDao<RoleDTO, Integer> {

	Page<RoleDTO> findAllByNameContainingOrDescriptionContaining(String searchText1,String searchText2, Pageable pageable);

}
