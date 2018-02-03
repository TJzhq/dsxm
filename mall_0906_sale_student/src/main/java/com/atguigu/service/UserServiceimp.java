package com.atguigu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mapper.UserMapper;
@Service
public class UserServiceimp implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Override
	public T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user) {
		
		return userMapper.selectUser(user);
	}

}
