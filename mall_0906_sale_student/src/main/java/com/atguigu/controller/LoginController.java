package com.atguigu.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.server.UserServer;
import com.atguigu.service.CartService;
import com.atguigu.service.UserService;
import com.atguigu.util.MyJsonUtil;

@Controller
public class LoginController {
	@Autowired
	private CartService cartService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserServer userSercer;
	@RequestMapping("tologin")
	public String tologin() {
		return "sale_login";
	}
	@RequestMapping("dologin")
	public String dologin(T_MALL_USER_ACCOUNT user, HttpSession session, HttpServletResponse response ,@CookieValue(value="list_cart_cookie",required=false)String list_cart_cookie) {
		//T_MALL_USER_ACCOUNT user1 = userService.login(user);
		T_MALL_USER_ACCOUNT user1 =userSercer.login(user);
		//System.out.println("111111111111111");
		if (user1 == null) {
			return "sale_login";
		} else {
			session.setAttribute("user", user1);
			String yh_nch = user1.getYh_nch();
			String yh_mch = user1.getYh_mch();

			Cookie cookie = null;
			Cookie cookie2 = null;
			try {
				cookie = new Cookie("yh_nch", URLEncoder.encode(yh_nch, "utf-8"));
				cookie2 = new Cookie("yh_mch", URLEncoder.encode(yh_mch, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			cookie.setMaxAge(60 * 60 * 24);
			cookie2.setMaxAge(60 * 60 * 24);
			// cookie.setPath("/");
			response.addCookie(cookie);
			response.addCookie(cookie2);
			List<T_MALL_SHOPPINGCAR>cart_list_db=cartService.getcartsbyyh_id(user1.getId());
			List<T_MALL_SHOPPINGCAR> list_cart_c = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
			if (cart_list_db==null) {
				 if (list_cart_cookie==null||list_cart_cookie=="") {
					
				}else {
					for (int i = 0; i < list_cart_c.size(); i++) {
						list_cart_c.get(i).setYh_id(user1.getId());
						cartService.addShoppingcart(list_cart_c.get(i));
					}
				}
			}else {
				if (list_cart_cookie==null||list_cart_cookie=="") {
					
				}else {
					for (int i = 0; i < list_cart_c.size(); i++) {
						int a=0;
						for (int j = 0; j < cart_list_db.size(); j++) {
							
							if (list_cart_c.get(i).getSku_id()==cart_list_db.get(j).getSku_id()) {
								cartService.updatacartbysku(list_cart_c.get(i).getTjshl(),list_cart_c.get(i).getSku_id());
								a++;
							}
							
						}
						if (a!=1) {
							list_cart_c.get(i).setYh_id(user1.getId());
							cartService.addShoppingcart(list_cart_c.get(i));
						}
					}
				}
			}
			
			cart_list_db=cartService.getcartsbyyh_id(user1.getId());
			session.setAttribute("cart_list", cart_list_db);
			Cookie cookies = new Cookie("list_cart_cookie", "");
			cookie.setMaxAge(60 * 60 * 24);
			response.addCookie(cookies);
			return "redirect:/index.do";
		}
	}
	@RequestMapping("loginout")
	public String loginout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/index.do";
	}
}
