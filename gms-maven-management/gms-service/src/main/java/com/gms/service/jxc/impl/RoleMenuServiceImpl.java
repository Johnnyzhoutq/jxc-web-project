package com.gms.service.jxc.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gms.entity.jxc.RoleMenu;
import com.gms.dao.repository.RoleMenuRepository;
import com.gms.service.jxc.RoleMenuService;

/**
 * 角色权限菜单关联Service实现类
 * @author jxc 
 *
 */
@Service("roleMenuService")
@Transactional
public class RoleMenuServiceImpl implements RoleMenuService{

	@Resource
	private RoleMenuRepository roleMenuRepository;

	@Override
	public void deleteByRoleId(Integer roleId) {
		roleMenuRepository.deleteByRoleId(roleId);
	}

	@Override
	public void save(RoleMenu roleMenu) {
		roleMenuRepository.save(roleMenu);
	}
	
	
}
