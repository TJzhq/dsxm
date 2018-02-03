package com.atguigu.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.service.CartService;
import com.atguigu.util.MyJsonUtil;

@Controller
public class CartController {
	@Autowired
	private CartService cartService;
	
	@RequestMapping("add_cart")
	public String add_cart(HttpServletResponse response, HttpSession session, T_MALL_SHOPPINGCAR cart,
			@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie,
			HttpServletRequest request, ModelMap map) {
		
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		if (user==null) {
			if (list_cart_cookie == null || list_cart_cookie.equals("")) {
				list_cart.add(cart);
			}else {
				list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
				int a=0;
				for (T_MALL_SHOPPINGCAR carts : list_cart) {
					if (carts.getSku_id()==cart.getSku_id()) {
						carts.setTjshl(carts.getTjshl()+cart.getTjshl());
						a++;
					}
				}
				if (a!=1) {
					list_cart.add(cart);
				}

			}
			Cookie cookie = new Cookie("list_cart_cookie", MyJsonUtil.list_to_json(list_cart));
			cookie.setMaxAge(60 * 60 * 24);
			response.addCookie(cookie);
		}else {
			list_cart = cartService.addShoppingcart(cart);
			session.setAttribute("list_cart", list_cart);
		}
		
		
		return "null";
	}
	@RequestMapping("toshoppingcart")
	public String goto_cart_list(ModelMap map, HttpSession session,
			@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie) {
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();

		if (user == null) {
			list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);

		} else {
			list_cart = cartService.getcartsbyyh_id(user.getId());

		}
		map.put("list_cart", list_cart);
		map.put("sum", get_sum(list_cart));
		return "sale_carts";
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
	@RequestMapping("change_cart_status")
	public String change_cart_status(HttpServletResponse response, int sku_id, String shfxz, ModelMap map,
			HttpSession session, @CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie) {

		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();

		// 修改购物车状态
		if (user == null) {
			list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
		} else {
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("cart_list_db");
		}

		for (int i = 0; i < list_cart.size(); i++) {
			if (sku_id == list_cart.get(i).getSku_id()) {
				// 更新session
				list_cart.get(i).setShfxz(shfxz);
				// 更新数据库
				if (user == null) {
					// 添加cookie
					Cookie cookie = new Cookie("list_cart_cookie", MyJsonUtil.list_to_json(list_cart));
					cookie.setMaxAge(60 * 60 * 24 * 7);
					response.addCookie(cookie);
				} else {
					// // 更新db
					cartService.update_cart(list_cart.get(i));
				}
			}
		}

		map.put("list_cart", list_cart);
		map.put("sum", get_sum(list_cart));
		return "sale_cart_list_inner";
	}
	

	
}
