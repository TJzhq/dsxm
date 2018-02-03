package com.atguigu.server;

import java.util.List;

import javax.jws.WebService;

import com.atguigu.bean.OBJ_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;

@WebService
public interface AdressServer {
	public List<T_MALL_ADDRESS> getallAddress();

	public T_MALL_ADDRESS getAddressById(Integer address_id);
}
