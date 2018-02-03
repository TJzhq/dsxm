package com.atguigu.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.MOD_SKU_SPU_ATTR;
import com.atguigu.bean.OBJ_SKU;
import com.atguigu.bean.OBJ_SKU_SPU_TM;
import com.atguigu.bean.Obj_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.service.AttrService;
import com.atguigu.service.SkuListService;
import com.atguigu.service.SkuService;


@Controller
public class SkuListController {
	@Autowired
	private SkuListService skuListService;
	@Autowired
	private AttrService attrService;
	@Autowired
	private SkuService skuService;
	@RequestMapping("tolist")
	public String tolist(int flbh2,Map<String,Object> map) {
		System.out.println(1111);
		map.put("flbh2", flbh2);
		return "sale_sku_list";
	}
	@RequestMapping("get_attr_list")
	public String get_attr_list(int flbh2,Map<String,Object> map) {
		List<Obj_T_MALL_ATTR>list= attrService.select_attrall(flbh2);
		map.put("attr_list", list);
		//List<Obj_T_MALL_ATTR>Sku_list_byflbh2= skuService.getSkuList(flbh2);
		
		
		return "sale_attr_inner";
	}
	@RequestMapping("get_sku_list_inner")
	public String get_sku_list_inner(int flbh2,Map<String,Object> map) {
		List<OBJ_SKU_SPU_TM>Sku_list_byflbh2= skuService.getSkuList(flbh2);
		map.put("sku_list_inner", Sku_list_byflbh2);
		map.put("flbh2", flbh2);
		System.out.println(flbh2);
		return "sale_sku_list_inner";
	}
	
	@RequestMapping("get_sku_list_by_attr")
	public String get_sku_list_by_attr(int flbh2,Map<String,Object> map,OBJ_SKU param) {
		List<OBJ_SKU_SPU_TM>Sku_list_byflbh2= skuService.getSkuList(flbh2,param.getList_pro_value());
		map.put("sku_list_inner", Sku_list_byflbh2);
		map.put("flbh2", flbh2);
		return "sale_sku_list_inner";
	}
	
	
	@RequestMapping("goto_sku_detail")
	public String goto_sku_detail(int sku_id,int spu_id,ModelMap map) {
		MOD_SKU_SPU_ATTR sku_detail=skuListService.getdetail(sku_id);
		
		List<T_MALL_SKU>sku_list = skuService.getskubyspu_id(spu_id);
		map.put("obj_sku", sku_detail);
		map.put("list_sku", sku_list);
		return "sale_search_detail";
	}
}
