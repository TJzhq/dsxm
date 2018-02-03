package com.atguigu.bean;

import java.util.List;

public class OBJ_T_MALL_ORDER extends T_MALL_ORDER {
	private List<OBJ_T_MALL_FLOW> flow_list;

	public List<OBJ_T_MALL_FLOW> getFlow_list() {
		return flow_list;
	}

	public void setFlow_list(List<OBJ_T_MALL_FLOW> flow_list) {
		this.flow_list = flow_list;
	}
}
