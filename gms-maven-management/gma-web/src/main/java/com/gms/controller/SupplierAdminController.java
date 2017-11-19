package com.gms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gms.entity.jxc.Log;
import com.gms.entity.jxc.Supplier;
import com.gms.entity.jxc.User;
import com.gms.service.jxc.LogService;
import com.gms.service.jxc.SupplierService;
import com.gms.util.Constant;

/**
 * 后台管理供应商Controller
 * @author jxc 
 *
 */
@RestController
@RequestMapping("/admin/supplier")
public class SupplierAdminController extends BaseController{
	
	@Resource
	private SupplierService supplierService;
	
	@Resource
	private LogService logService;
	
	/**
	 * 分页查询供应商信息
	 * @param supplier
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions(value = { "供应商管理" })
	public Map<String,Object> list(Supplier supplier,@RequestParam(value="page",required=false)Integer page,@RequestParam(value="rows",required=false)Integer rows,
			HttpServletRequest request)throws Exception{
		User currentUser = getCurrentUser(request);
		if(currentUser.getUserType().equals(Constant.SHOPTYPE)){
			supplier.setShopId(currentUser.getShopId());
		}
		List<Supplier> supplierList=supplierService.list(supplier, page, rows, Direction.ASC, "id");
		Long total=supplierService.getCount(supplier);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("rows", supplierList);
		resultMap.put("total", total);
		logService.save(new Log(Log.SEARCH_ACTION,"查询供应商信息")); // 写入日志
		return resultMap;
	}
	
	/**
	 * 下拉框模糊查询
	 * @param q
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/comboList")
	@RequiresPermissions(value = {"进货入库","退货出库","进货单据查询","退货单据查询"},logical=Logical.OR)
	public List<Supplier> comboList(HttpServletRequest request,String q)throws Exception{
		User currentUser = getCurrentUser(request);
		if(q==null){
			q="";
		}
		if(currentUser.getUserType().equals(Constant.SHOPTYPE)){
			return supplierService.findByShopAndName(currentUser.getShopId(),"%"+q+"%");
		}
		return supplierService.findByName("%"+q+"%");
	}
	
	
	
	/**
	 * 添加或者修改供应商信息
	 * @param supplier
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	@RequiresPermissions(value = { "供应商管理" })
	public Map<String,Object> save(Supplier supplier,HttpServletRequest request)throws Exception{
		if(supplier.getId()!=null){ // 写入日志
			logService.save(new Log(Log.UPDATE_ACTION,"更新供应商信息"+supplier)); 
		}else{
			logService.save(new Log(Log.ADD_ACTION,"添加供应商信息"+supplier)); 
		}
		User currentUser = getCurrentUser(request);
		if(currentUser.getUserType().equals(Constant.SHOPTYPE)){
			supplier.setShopId(currentUser.getShopId());
		}
		Map<String, Object> resultMap = new HashMap<>();
		supplierService.save(supplier);			
		resultMap.put("success", true);
		return resultMap;
	}
	
	
	/**
	 * 删除供应商信息
	 * @param id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value = { "供应商管理" })
	public Map<String,Object> delete(String ids)throws Exception{
		Map<String, Object> resultMap = new HashMap<>();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			int id=Integer.parseInt(idsStr[i]);
			logService.save(new Log(Log.DELETE_ACTION,"删除供应商信息"+supplierService.findById(id)));  // 写入日志
			supplierService.delete(id);							
		}
		resultMap.put("success", true);
		return resultMap;
	}

}
