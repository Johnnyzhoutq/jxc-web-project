package com.gms.controller;

import javax.servlet.http.HttpServletRequest;

import com.gms.entity.jxc.User;

public class BaseController {
	/**
	 * 获取Session中保存的当前User Info
	*/
	public User getCurrentUser(HttpServletRequest request){
		User currentUser= (User)request.getSession().getAttribute("currentUser");
		return currentUser;
	}
}
