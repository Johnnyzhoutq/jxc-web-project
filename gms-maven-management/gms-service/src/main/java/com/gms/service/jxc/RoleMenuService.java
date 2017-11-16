package com.gms.service.jxc;

import com.gms.entity.jxc.RoleMenu;

/**
 * 角色权限关联Service接口
 * @author jxc 
 *
 */
public interface RoleMenuService {

	/**
	 * 根据角色id删除所有关联信息
	 * @param id
	 */
	public void deleteByRoleId(Integer roleId);
	
	/**
	 * 保存
	 * @param roleMenu
	 */
	public void save(RoleMenu roleMenu);
}
