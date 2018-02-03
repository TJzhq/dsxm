package com.atguigu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJ_SKU;
import com.atguigu.bean.OBJ_SKU_SPU_TM;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mapper.SkuMapper;
@Service
public class SkuServiceimp implements SkuService {
	@Autowired
	private SkuMapper skuMapper;
	@Override
	public void saveSku(OBJ_SKU obj) {
		
		skuMapper.insertSku(obj);
		List<T_MALL_SKU_ATTR_VALUE> list_pro_value = obj.getList_pro_value();
		for (T_MALL_SKU_ATTR_VALUE t_MALL_SKU_ATTR_VALUE : list_pro_value) {
			if (t_MALL_SKU_ATTR_VALUE.getShxm_id()!=0) {
				t_MALL_SKU_ATTR_VALUE.setShp_id(obj.getShp_id());
				t_MALL_SKU_ATTR_VALUE.setSku_id(obj.getId());
				skuMapper.insertSkuAV(t_MALL_SKU_ATTR_VALUE);
			}
		
		}
		
	}
	@Override
	public List<OBJ_SKU_SPU_TM> getSkuList(int flbh2) {
		
		return skuMapper.getSkuList(flbh2);
	}
	@Override
	public List<OBJ_SKU_SPU_TM> getSkuList(int flbh2, List<T_MALL_SKU_ATTR_VALUE> list_pro_value) {
		StringBuffer sql=new StringBuffer();
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("flbh2", flbh2);
		if (list_pro_value.size()==0||list_pro_value==null) {
			map.put("sql", "");
		}else {
			sql.append("		select sku.id as sku_id , spu.id as spu_id , tm.id as\r\n" + 
					"		tm_id ,\r\n" + 
					"		sku.*,spu.*,tm.*  from \r\n" + 
					"		t_mall_sku sku,\r\n" + 
					"		t_mall_product spu,\r\n" + 
					"		t_mall_trade_mark tm \r\n" + 
					"		where \r\n" + 
					"		sku.shp_id = spu.Id AND\r\n" + 
					"		spu.pp_id = tm.Id and \r\n" + 
					"		spu.flbh2 = "+flbh2+"  ");
			sql.append(" and sku.id in (select sku0.sku_id from ");
			//map.put("sqlpre", " and sku.id in (select sku_id from #");
			for (int i = 0; i < list_pro_value.size(); i++) {
				
				sql.append(" (select sku_id from t_mall_sku_attr_value where shxm_id = "+list_pro_value.get(i).getShxm_id()+" and shxzh_id ="+list_pro_value.get(i).getShxzh_id()+") sku"+i+" ");
				if (i<list_pro_value.size()-1) {
					sql.append(" , ");
				}
			}
			if (list_pro_value.size()>1) {
				sql.append(" where ");
				for (int i = 1; i < list_pro_value.size(); i++) {
					sql.append(" Sku"+(i-1)+".sku_id = sku"+i+".sku_id ");
					if (i<list_pro_value.size()-2) {
						sql.append(" and ");
					}
				}
			}
			
			sql.append(" )  ");
			
			map.put("sql", sql.toString());
			System.out.println(sql.toString());
		}
		return skuMapper.getSkuListbytj(map);
	}
	@Override
	public List<T_MALL_SKU> getskubyspu_id(int spu_id) {
		return skuMapper.get_sku_list(spu_id);
	}

}
