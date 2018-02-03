package com.atguigu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.mapper.CartMapper;
@Service
public class CartServiceImp implements CartService {
	@Autowired
	private CartMapper cartMapper;

	@Override
	public List<T_MALL_SHOPPINGCAR> addShoppingcart(T_MALL_SHOPPINGCAR cart) {
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		list_cart =	cartMapper.getcartBYyh_id(cart.getYh_id());
		if (list_cart==null) {
			cartMapper.insertCart(cart);
		}else {
			int a=0;
			for (T_MALL_SHOPPINGCAR carts : list_cart) {
				if (carts.getSku_id()==cart.getSku_id()) {
					Map<String,Object> map =new HashMap<String,Object>();
					map.put("tjshl", carts.getTjshl()+cart.getTjshl());
					map.put("sku_id", carts.getSku_id());
					cartMapper.updateTJSHL(map);
					a++;
				}
			}
			if (a!=1) {
				cartMapper.insertCart(cart);
			}
		}
		
		return cartMapper.getcartBYyh_id(cart.getYh_id());
	}

	@Override
	public List<T_MALL_SHOPPINGCAR> getcartsbyyh_id(int id) {
		return cartMapper.getcartBYyh_id(id);
	}

	@Override
	public void updatacartbysku(int tjshl,int i) {
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("tjshl", tjshl);
		map.put("sku_id", i);
		cartMapper.updateTJSHL(map);
	}

	@Override
	public void update_cart(T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR) {
		cartMapper.updatecart(t_MALL_SHOPPINGCAR);
	}

}
