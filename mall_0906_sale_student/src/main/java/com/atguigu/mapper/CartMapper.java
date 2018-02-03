package com.atguigu.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;

public interface CartMapper {

	List<T_MALL_SHOPPINGCAR> getcartBYyh_id(int yh_id);

	void updateTJSHL(Map<String,Object>map);

	void insertCart(T_MALL_SHOPPINGCAR cart);

	void updatecart(T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR);

}
