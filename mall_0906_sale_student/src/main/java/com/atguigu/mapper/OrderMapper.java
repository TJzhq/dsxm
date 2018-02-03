package com.atguigu.mapper;

import java.util.List;

import com.atguigu.bean.OBJ_T_MALL_FLOW;
import com.atguigu.bean.OBJ_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ORDER_INFO;

public interface OrderMapper {

	void save_order(OBJ_T_MALL_ORDER order);

	void save_flow(OBJ_T_MALL_FLOW obj_T_MALL_FLOW);

	void save_info(T_MALL_ORDER_INFO t_MALL_ORDER_INFO);

	void delete_shoppingCart(List<Integer> list_cart_id);

}
