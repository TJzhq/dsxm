package com.atguigu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJ_T_MALL_FLOW;
import com.atguigu.bean.OBJ_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_ORDER_INFO;
import com.atguigu.mapper.OrderMapper;
@Service
public class OrderServiceImp implements OrderService {
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public void saveAll(OBJ_T_MALL_ORDER order, T_MALL_ADDRESS address) {
		// T_MALL_ADDRESS address=adressServer.getaddressById(address_id);
		order.setShhr(address.getShjr());
		order.setJdh(1);
		order.setDzh_id(address.getId());
		order.setDzh_mch(address.getYh_dz());
		orderMapper.save_order(order);
		List<Integer> list_cart_id = new ArrayList<Integer>();
		List<OBJ_T_MALL_FLOW> flow_list = order.getFlow_list();
		for (int i = 0; i < flow_list.size(); i++) {
			flow_list.get(i).setPsmsh("老司机");
			flow_list.get(i).setDd_id(order.getId());
			flow_list.get(i).setMdd(address.getYh_dz());

			orderMapper.save_flow(flow_list.get(i));
			List<T_MALL_ORDER_INFO> info_list = flow_list.get(i).getInfo_list();
			for (int j = 0; j < info_list.size(); j++) {
				info_list.get(j).setDd_id(order.getId());
				info_list.get(j).setFlow_id(flow_list.get(i).getId());
				orderMapper.save_info(info_list.get(j));
				list_cart_id.add(info_list.get(j).getGwch_id());
			}

		}
		orderMapper.delete_shoppingCart(list_cart_id);
	}

}
