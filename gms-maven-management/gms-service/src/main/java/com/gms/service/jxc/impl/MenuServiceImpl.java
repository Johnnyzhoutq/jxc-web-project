package com.gms.service.jxc.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gms.entity.jxc.Menu;
import com.gms.dao.repository.MenuRepository;
import com.gms.service.jxc.MenuService;

/**
 * 权限菜单Service实现类
 * @author jxc 
 *
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService{

	@Resource
	private MenuRepository menuRepository;
	
	@Override
	public List<Menu> findByRoleId(int roleId) {
		return menuRepository.findByRoleId(roleId);
	}

	@Override
	public List<Menu> findByParentIdAndRoleId(int parentId, int roleId) {
		return menuRepository.findByParentIdAndRoleId(parentId, roleId);
	}

	@Override
	public List<Menu> findByParentId(int parentId) {
		return menuRepository.findByParentId(parentId);
	}

	@Override
	public Menu findById(Integer id) {
		return menuRepository.findOne(id);
	}

}
