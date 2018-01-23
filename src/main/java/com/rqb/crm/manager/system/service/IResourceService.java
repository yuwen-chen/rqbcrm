package com.rqb.crm.manager.system.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.rqb.crm.manager.common.base.service.IBaseService;
import com.rqb.crm.manager.system.dto.ResourceDTO;
import com.rqb.crm.manager.vo.ZtreeView;

/**
 * <p>
 * 资源服务类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
public interface IResourceService extends IBaseService<ResourceDTO, Integer> {

	/**
	 * 获取角色的权限树
	 * @param roleId
	 * @return
	 */
	List<ZtreeView> tree(int roleId);

	/**
	 * 修改或者新增资源
	 * @param resource
	 */
	void saveOrUpdate(ResourceDTO resource);

	/**
	 * 关键字分页
	 * @param searchText
	 * @param pageRequest
	 * @return
	 */
	Page<ResourceDTO> findAllByLike(String searchText, PageRequest pageRequest);

}
