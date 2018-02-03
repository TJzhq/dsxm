package com.atguigu.service;

import com.atguigu.bean.OBJ_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;

public interface OrderService {

	void saveAll(OBJ_T_MALL_ORDER order, T_MALL_ADDRESS address);

}
