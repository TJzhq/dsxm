package com.atguigu.bean;

import java.util.List;

public class MOD_SKU_SPU_ATTR extends T_MALL_SKU {
	private T_MALL_PRODUCT spu;
	private List<MOD_ATTR_NAME_VALUE> list_attr_name_value;
	private List<T_MALL_PRODUCT_IMAGE>list_image;
	public T_MALL_PRODUCT getSpu() {
		return spu;
	}
	public void setSpu(T_MALL_PRODUCT spu) {
		this.spu = spu;
	}
	public List<MOD_ATTR_NAME_VALUE> getList_attr_name_value() {
		return list_attr_name_value;
	}
	public void setList_attr_name_value(List<MOD_ATTR_NAME_VALUE> list_attr_name_value) {
		this.list_attr_name_value = list_attr_name_value;
	}
	public List<T_MALL_PRODUCT_IMAGE> getList_image() {
		return list_image;
	}
	public void setList_image(List<T_MALL_PRODUCT_IMAGE> list_image) {
		this.list_image = list_image;
	}

}
