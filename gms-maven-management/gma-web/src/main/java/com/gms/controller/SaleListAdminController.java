package com.gms.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gms.entity.jxc.Log;
import com.gms.entity.jxc.SaleCount;
import com.gms.entity.jxc.SaleList;
import com.gms.entity.jxc.SaleListGoods;
import com.gms.entity.jxc.User;
import com.gms.service.jxc.LogService;
import com.gms.service.jxc.SaleListGoodsService;
import com.gms.service.jxc.SaleListService;
import com.gms.service.jxc.UserService;
import com.gms.util.Constant;
import com.gms.util.DateUtil;
import com.gms.util.MathUtil;
import com.gms.util.StringUtil;

/**
 * 销售单Controller类
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/saleList")
public class SaleListAdminController extends BaseController{

	@Resource
	private SaleListService saleListService;
	
	@Resource
	private SaleListGoodsService saleListGoodsService;
	
	@Resource
	private LogService logService;
	
	@Resource
	private UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
	}
	
	/**
	 * 根据条件分页查询销售单信息
	 * @param saleList
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions(value = { "销售单据查询" })
	public Map<String,Object> list(SaleList saleList,HttpServletRequest request)throws Exception{
		User currentUser = getCurrentUser(request);
		if(currentUser.getUserType().equals(Constant.SHOPTYPE)){
			saleList.setShopId(currentUser.getShopId());
		}
		Map<String, Object> resultMap = new HashMap<>();
		List<SaleList> saleListList=saleListService.list(saleList, Direction.DESC, "saleDate");
		resultMap.put("rows", saleListList);
		return resultMap;
	}
	
	/**
	 * 根据销售单id查询所有销售单商品
	 * @param saleListId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listGoods")
	@RequiresPermissions(value = { "销售单据查询" })
	public Map<String,Object> listGoods(Integer saleListId)throws Exception{
		if(saleListId==null){
			return null;
		}
		Map<String, Object> resultMap = new HashMap<>();
		List<SaleListGoods> saleListGoodsList=saleListGoodsService.listBySaleListId(saleListId);
		resultMap.put("rows", saleListGoodsList);
		return resultMap;
	}
	
	/**
	 * 客户统计 获取销售单的所有商品信息
	 * @param saleList
	 * @param saleListGoods
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listCount")
	@RequiresPermissions(value = { "客户统计" })
	public Map<String,Object> listCount(SaleList saleList,SaleListGoods saleListGoods,
			HttpServletRequest request)throws Exception{
		User currentUser = getCurrentUser(request);
		if(currentUser.getUserType().equals(Constant.SHOPTYPE)){
			saleList.setShopId(currentUser.getShopId());
		}
		Map<String, Object> resultMap = new HashMap<>();
		List<SaleList> saleListList=saleListService.list(saleList, Direction.DESC, "saleDate");
		for(SaleList pl:saleListList){
			saleListGoods.setSaleList(pl);
			List<SaleListGoods> plgList=saleListGoodsService.list(saleListGoods);
			for(SaleListGoods plg:plgList){
				plg.setSaleList(null);
			}
			pl.setSaleListGoodsList(plgList);
		}
		resultMap.put("rows", saleListList);
		return resultMap;
	}
	
	/**
	 * 获取销售单号
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getSaleNumber")
	@RequiresPermissions(value = {"销售出库"})
	public String genBillCode(String type)throws Exception{
		StringBuffer biilCodeStr=new StringBuffer();
		biilCodeStr.append("XS");
		biilCodeStr.append(DateUtil.getCurrentDateStr()); // 拼接当前日期
		String saleNumber=saleListService.getTodayMaxSaleNumber(); // 获取当天最大的销售单号
		if(saleNumber!=null){
			biilCodeStr.append(StringUtil.formatCode(saleNumber));
		}else{
			biilCodeStr.append(Constant.DEFAULT_TABLE_CODE);
		}
		return biilCodeStr.toString();
	}
	
	/**
	 * 修改销售单的支付状态
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions(value = {"客户统计"})
	public Map<String,Object> update(Integer id)throws Exception{
		Map<String, Object> resultMap = new HashMap<>();
		SaleList saleList=saleListService.findById(id);
		saleList.setState(1); // 修改成支付状态
		saleListService.update(saleList);
		resultMap.put("success", true);	
		return resultMap;
	}
	
	/**
	 * 添加销售单 以及所有销售单商品
	 * @param saleList
	 * @param goodsJson
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions(value = {"销售出库"})
	public Map<String,Object> save(SaleList saleList,String goodsJson,
			HttpServletRequest request)throws Exception{
		User currentUser = getCurrentUser(request);
		if(currentUser.getUserType().equals(Constant.SHOPTYPE)){
			saleList.setShopId(currentUser.getShopId());
		}
		Map<String, Object> resultMap = new HashMap<>();
		saleList.setUser(userService.findByUserName((String) SecurityUtils.getSubject().getPrincipal())); // 设置操作用户
		Gson gson = new Gson();
		List<SaleListGoods> plgList=gson.fromJson(goodsJson, new TypeToken<List<SaleListGoods>>(){}.getType());
		saleListService.save(saleList, plgList);
		logService.save(new Log(Log.ADD_ACTION,"添加销售单")); 
		resultMap.put("success", true);	
		return resultMap;
	}
	
	/**
	 * 根据id删除销售单信息 包括销售单里的商品
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value = { "销售单据查询" })
	public Map<String,Object> delete(Integer id)throws Exception{
		Map<String, Object> resultMap = new HashMap<>();
		saleListService.delete(id);
		logService.save(new Log(Log.DELETE_ACTION,"删除销售单信息"+saleListService.findById(id)));  // 写入日志
		resultMap.put("success", true);		
		return resultMap;
	}
	
	/**
	 * 按日统计分析
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/countSaleByDay")
	@RequiresPermissions(value = { "按日统计分析" })
	public Map<String,Object> countSaleByDay(String begin,String end,HttpServletRequest request)throws Exception{
		User currentUser = getCurrentUser(request);
		Map<String, Object> resultMap = new HashMap<>();
		List<SaleCount> scdList=new ArrayList<SaleCount>();
		List<String> datas=DateUtil.getRangeDates(begin, end);
		List<Object> ll= saleListService.countSaleByDay(begin, end,currentUser.getShopId());
		for(String data:datas){
			SaleCount scd=new SaleCount();
			scd.setDate(data);
			boolean flag=true;
			for(Object o:ll){
				Object []oo=(Object[])o;
				String dd=oo[2].toString().substring(0, 10);
				if(dd.equals(data)){ // 存在
					scd.setAmountCost(MathUtil.format2Bit(Float.parseFloat(oo[0].toString())));
					scd.setAmountSale(MathUtil.format2Bit(Float.parseFloat(oo[1].toString())));
					scd.setAmountProfit(MathUtil.format2Bit(scd.getAmountSale()-scd.getAmountCost()));
					flag=false;
				}
			}
			if(flag){
				scd.setAmountCost(0);
				scd.setAmountSale(0);
				scd.setAmountProfit(0);
			}
			scdList.add(scd);
		}
		resultMap.put("rows", scdList);		
		resultMap.put("success", true);		
		return resultMap;
	}
	
	/**
	 * 按月统计分析
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/countSaleByMonth")
	@RequiresPermissions(value = { "按月统计分析" })
	public Map<String,Object> countSaleByMonth(String begin,String end,HttpServletRequest request)throws Exception{
		User currentUser = getCurrentUser(request);
		Map<String, Object> resultMap = new HashMap<>();
		List<SaleCount> scList=new ArrayList<SaleCount>();
		List<String> datas=DateUtil.getRangeMonth(begin, end);
		List<Object> ll= saleListService.countSaleByMonth(begin, end, currentUser.getShopId());
		for(String data:datas){
			SaleCount sc=new SaleCount();
			sc.setDate(data);
			boolean flag=true;
			for(Object o:ll){
				Object []oo=(Object[])o;
				String dd=oo[2].toString().substring(0, 7);
				if(dd.equals(data)){ // 存在
					sc.setAmountCost(MathUtil.format2Bit(Float.parseFloat(oo[0].toString())));
					sc.setAmountSale(MathUtil.format2Bit(Float.parseFloat(oo[1].toString())));
					sc.setAmountProfit(MathUtil.format2Bit(sc.getAmountSale()-sc.getAmountCost()));
					flag=false;
				}
			}
			if(flag){
				sc.setAmountCost(0);
				sc.setAmountSale(0);
				sc.setAmountProfit(0);
			}
			scList.add(sc);
		}
		resultMap.put("rows", scList);		
		resultMap.put("success", true);		
		return resultMap;
	}
}
