package com.gms.service.jxc.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gms.entity.jxc.UserRole;
import com.gms.dao.repository.UserRoleRepository;
import com.gms.service.jxc.UserRoleService;

/**
 * 用户角色关联Service实现类
 * @author jxc 
 *
 */
@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl implements UserRoleService{

	@Resource
	private UserRoleRepository userRoleRepository;
	
	@Override
	public void save(UserRole userRole) {
		userRoleRepository.save(userRole);
	}



	@Override
	public void delete(UserRole userRole) {
		userRoleRepository.delete(userRole);
	}

	@Override
	public UserRole findById(Integer id) {
		return userRoleRepository.findOne(id);
	}

	@Override
	public void deleteByUserId(Integer userId) {
		userRoleRepository.deleteByUserId(userId);
	}

	@Override
	public void deleteByRoleId(Integer userId) {
		userRoleRepository.deleteByRoleId(userId);
	}

	

}
