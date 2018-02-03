package com.atguigu.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.atguigu.bean.OBJ_T_MALL_FLOW;
import com.atguigu.bean.OBJ_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_ORDER_INFO;
import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.server.AdressServer;
import com.atguigu.service.CartService;
import com.atguigu.service.OrderService;

@Controller
@SessionAttributes("order")
public class OrderController {
	@Autowired 
	private CartService cartService;
	@Autowired
	private AdressServer adressServer;
	@Autowired 
	private OrderService orderSercice;
	@RequestMapping("gotopay")
	public String gotopay(HttpSession session, ModelMap map) {
		if (session.getAttribute("user") == null) {
			return "redirect:/dologin.do";
		} else {
			List<T_MALL_SHOPPINGCAR> list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart");
			Set<String> kcdz_set = new HashSet<String>();
			for (T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR : list_cart) {
				kcdz_set.add(t_MALL_SHOPPINGCAR.getKcdz());
			}
			Iterator<String> iterator = kcdz_set.iterator();
			OBJ_T_MALL_ORDER obj_T_MALL_ORDER = new OBJ_T_MALL_ORDER();
			List<OBJ_T_MALL_FLOW> flow_list = new ArrayList<OBJ_T_MALL_FLOW>();
			while (iterator.hasNext()) {
				OBJ_T_MALL_FLOW obj_T_MALL_FLOW = new OBJ_T_MALL_FLOW();
				List<T_MALL_ORDER_INFO> info_list = new ArrayList<T_MALL_ORDER_INFO>();
				String kcdz = iterator.next();
				for (int i = 0; i < list_cart.size(); i++) {
					if (list_cart.get(i).getKcdz().equals(kcdz) && list_cart.get(i).getShfxz().equals("1")) {
						T_MALL_ORDER_INFO t_MALL_ORDER_INFO = new T_MALL_ORDER_INFO();

						// sku_id(skuid) Integer
						t_MALL_ORDER_INFO.setSku_id(list_cart.get(i).getSku_id());
						// sku_mch(sku名称) String(255)
						t_MALL_ORDER_INFO.setSku_mch(list_cart.get(i).getSku_mch());
						// shp_tp(商品图片) String(255)
						t_MALL_ORDER_INFO.setShp_tp(list_cart.get(i).getShp_tp());
						// sku_jg(sku价格) decimal
						t_MALL_ORDER_INFO.setSku_jg(list_cart.get(i).getSku_jg());
						// sku_shl(sku数量) Integer
						t_MALL_ORDER_INFO.setSku_shl(list_cart.get(i).getTjshl());
						// sku_kcdz(sku库存地址) String(255)
						t_MALL_ORDER_INFO.setSku_kcdz(list_cart.get(i).getKcdz());
						// gwch_id(购物车id) Integer
						t_MALL_ORDER_INFO.setGwch_id(list_cart.get(i).getId());
						info_list.add(t_MALL_ORDER_INFO);
						// obj_T_MALL_FLOW.setMqdd(list_cart.get(i).getKcdz());
					}
				}
				obj_T_MALL_FLOW.setInfo_list(info_list);
				obj_T_MALL_FLOW.setMqdd(kcdz);
				obj_T_MALL_FLOW.setPsfsh("老司机张迎超");
				obj_T_MALL_FLOW.setYh_id(((T_MALL_USER_ACCOUNT) session.getAttribute("user")).getId());
				flow_list.add(obj_T_MALL_FLOW);
			}
			obj_T_MALL_ORDER.setFlow_list(flow_list);
			obj_T_MALL_ORDER.setJdh(0);
			obj_T_MALL_ORDER.setYh_id(((T_MALL_USER_ACCOUNT) session.getAttribute("user")).getId());
			obj_T_MALL_ORDER.setZje(get_sum(list_cart));
			map.put("order", obj_T_MALL_ORDER);

			System.out.println(obj_T_MALL_ORDER);
			List<T_MALL_ADDRESS> address = adressServer.getallAddress();
			map.put("list_address", address);
			return "sale_check_order";
		}
	}
	@RequestMapping("save_order")
	public String save_order(HttpSession session,@ModelAttribute("order") OBJ_T_MALL_ORDER order ,Integer address_id) {
		
		T_MALL_ADDRESS address = adressServer.getAddressById(address_id);
		orderSercice.saveAll(order,address);
		//删除购物车
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		session.setAttribute("list_cart_session", cartService.getcartsbyyh_id(user.getId()));
		return null;
	}
	
	private BigDecimal get_sum(List<T_MALL_SHOPPINGCAR> list_cart) {
		BigDecimal sum = new BigDecimal("0");
		if (list_cart!=null) {
			for (int i = 0; i < list_cart.size(); i++) {
				if (list_cart.get(i).getShfxz().equals("1")) {
					sum = sum.add(new BigDecimal(list_cart.get(i).getHj() + ""));
				}

			}
		}
		

		return sum;
	}
}
