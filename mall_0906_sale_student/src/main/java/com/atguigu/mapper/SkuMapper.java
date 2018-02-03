package com.atguigu.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu.bean.OBJ_SKU;
import com.atguigu.bean.OBJ_SKU_SPU_TM;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;

public interface SkuMapper {

	void insertSku(OBJ_SKU obj);

	void insertSkuAV(T_MALL_SKU_ATTR_VALUE t_MALL_SKU_ATTR_VALUE);

	List<OBJ_SKU_SPU_TM> getSkuList(int flbh2);

	List<OBJ_SKU_SPU_TM> getSkuListbytj(Map<String,Object> map);

	List<T_MALL_SKU> get_sku_list(int spu_id);

}
