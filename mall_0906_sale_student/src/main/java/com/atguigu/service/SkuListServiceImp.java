package com.atguigu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.MOD_SKU_SPU_ATTR;
import com.atguigu.mapper.SkuListMapper;
@Service
public class SkuListServiceImp implements SkuListService {
	@Autowired
	private SkuListMapper skuListMapper;

	@Override
	public MOD_SKU_SPU_ATTR getdetail(int sku_id) {
		return skuListMapper.select_sku_detail(sku_id);
	}

}
