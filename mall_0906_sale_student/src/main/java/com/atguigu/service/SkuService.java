package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.OBJ_SKU;
import com.atguigu.bean.OBJ_SKU_SPU_TM;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;

public interface SkuService {

	void saveSku(OBJ_SKU obj);

	List<OBJ_SKU_SPU_TM> getSkuList(int flbh2);

	List<OBJ_SKU_SPU_TM> getSkuList(int flbh2, List<T_MALL_SKU_ATTR_VALUE> list_pro_value);

	List<T_MALL_SKU> getskubyspu_id(int spu_id);

}
