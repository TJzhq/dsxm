package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;

public interface CartService {

	List<T_MALL_SHOPPINGCAR> addShoppingcart(T_MALL_SHOPPINGCAR cart);

	List<T_MALL_SHOPPINGCAR> getcartsbyyh_id(int id);

	void updatacartbysku(int tjshl, int i);

	void update_cart(T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR);

}
