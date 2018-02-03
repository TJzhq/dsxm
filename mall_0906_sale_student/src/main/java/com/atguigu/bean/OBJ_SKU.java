package com.atguigu.bean;

import java.util.List;

public class OBJ_SKU extends T_MALL_SKU {
	private List<T_MALL_SKU_ATTR_VALUE> list_pro_value;

	public List<T_MALL_SKU_ATTR_VALUE> getList_pro_value() {
		return list_pro_value;
	}

	public void setList_pro_value(List<T_MALL_SKU_ATTR_VALUE> list_pro_value) {
		this.list_pro_value = list_pro_value;
	}
}
